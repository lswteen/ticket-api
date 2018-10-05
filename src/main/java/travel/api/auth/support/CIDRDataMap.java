package travel.api.auth.support;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by we on 2017. 4. 6..
 */
@Component
public class CIDRDataMap {

    private Map<String, RangeAddress> rangeAddressMap;


    public CIDRDataMap() {
        rangeAddressMap = new HashMap<String,RangeAddress>();
    }

    public Map<String, RangeAddress> getRangeAddressMap() {
        return rangeAddressMap;
    }

    public RangeAddress getRangeAddress(String cidr) {
        return rangeAddressMap.get(cidr);
    }


    private RangeAddress calculate(String cidr) throws UnknownHostException {

        // address to cidr address
        if (!cidr.contains("/")) {

            //generalAddress to CIDR Address
            InetAddress inetAddress = InetAddress.getByName(cidr);

            if (inetAddress.getAddress().length == 4) {
                cidr=cidr +"/32";
            } else {
                cidr=cidr +"/128";
            }
        }


        InetAddress inetAddress = null;
        int prefixLength = 0;

        /* split CIDR to address and prefix part */
        if (cidr.contains("/")) {
            int index = cidr.indexOf("/");
            String addressPart = cidr.substring(0, index);
            String networkPart = cidr.substring(index + 1);

            inetAddress = InetAddress.getByName(addressPart);
            prefixLength = Integer.parseInt(networkPart);

        } else {
            throw new IllegalArgumentException("not an valid CIDR format!");
        }


        ByteBuffer maskBuffer;
        int targetSize;
        if (inetAddress.getAddress().length == 4) {
            maskBuffer =
                    ByteBuffer
                            .allocate(4)
                            .putInt(-1);
            targetSize = 4;
        } else {
            maskBuffer = ByteBuffer.allocate(16)
                    .putLong(-1L)
                    .putLong(-1L);
            targetSize = 16;
        }

        BigInteger mask = (new BigInteger(1, maskBuffer.array())).not().shiftRight(prefixLength);

        ByteBuffer buffer = ByteBuffer.wrap(inetAddress.getAddress());
        BigInteger ipVal = new BigInteger(1, buffer.array());

        BigInteger startIp = ipVal.and(mask);
        BigInteger endIp = startIp.add(mask.not());

        byte[] startIpArr = toBytes(startIp.toByteArray(), targetSize);
        byte[] endIpArr = toBytes(endIp.toByteArray(), targetSize);

        return new RangeAddress(InetAddress.getByAddress(startIpArr), InetAddress.getByAddress(endIpArr));
    }


    private byte[] toBytes(byte[] array, int targetSize) {
        int counter = 0;
        List<Byte> newArr = new ArrayList<Byte>();
        while (counter < targetSize && (array.length - 1 - counter >= 0)) {
            newArr.add(0, array[array.length - 1 - counter]);
            counter++;
        }

        int size = newArr.size();
        for (int i = 0; i < (targetSize - size); i++) {

            newArr.add(0, (byte) 0);
        }

        byte[] ret = new byte[newArr.size()];
        for (int i = 0; i < newArr.size(); i++) {
            ret[i] = newArr.get(i);
        }
        return ret;
    }


    /**
     * CIDR 주소 안에 ipAddress가 포함되는지 체크
     *
     * 단 일반 주소가 나올경우 ipv4는 끝에 /32, ipv6는 끝에 /128로 붙여서 CIDR주소를 계산함.
     * @param cidr
     * @param ipAddress
     * @return
     * @throws UnknownHostException
     */
    public boolean isInRange(String cidr, String ipAddress) throws UnknownHostException {

        //cache (map에서 가져옴)
        RangeAddress rangeAddress = rangeAddressMap.get(cidr);

        if (rangeAddress == null) {
            rangeAddress = calculate(cidr);
            rangeAddressMap.put(cidr,  rangeAddress);
        }

        return isInRange(rangeAddress, ipAddress);
    }


    private boolean isInRange(RangeAddress rangeAddress,String ipAddress) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(ipAddress);
        BigInteger start = new BigInteger(1, rangeAddress.getStartAddress().getAddress());
        BigInteger end = new BigInteger(1, rangeAddress.getEndAddress().getAddress());
        BigInteger target = new BigInteger(1, address.getAddress());

        int st = start.compareTo(target);
        int te = target.compareTo(end);

        return (st == -1 || st == 0) && (te == -1 || te == 0);
    }


    public class RangeAddress {
        private InetAddress startAddress;
        private InetAddress endAddress;


        public RangeAddress(InetAddress startAddress, InetAddress endAddress) {
            this.startAddress = startAddress;
            this.endAddress =endAddress;
        }
        public InetAddress getStartAddress() {
            return startAddress;
        }

        public InetAddress getEndAddress() {
            return endAddress;
        }

        public String toString() {
            return startAddress.getHostAddress()+" ~ " +endAddress.getHostAddress();
        }
    }
}
