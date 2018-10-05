package travel.api.auth.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel.commons.utility.StringUtil;

import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by we on 2017. 4. 6..
 */
@Component
public class IpReferrerValidator {

    @Autowired
    private CIDRDataMap cidrDataMap;

    public boolean validateForAuthIp(String remoteIp, List<String> allowIpList) {

        //허용 IP 목록이 없는 경우, 무조건 TRUE(validate)
        if (allowIpList == null || allowIpList.size() == 0) return true;

        for(String allowIp : allowIpList) {
            try {
                boolean ret =  cidrDataMap.isInRange(allowIp, remoteIp);
                if (ret) {
                    return true;
                }
            } catch(UnknownHostException e) {
                continue;
            }
        }
        return false;
    }


    public boolean validateForAuthReferrer(String refererHostAndPort, String allowReferrer) {
        String regExp = toReferrerRegExp(allowReferrer);
        if (refererHostAndPort.matches(regExp)) {
            return true;
        }
        return false;
    }

    public boolean validateForAuthReferrer(String refererHostAndPort, List<String> allowReferrerList) {


        //허용 Referrer 목록이 없는 경우, 무조건 TRUE(validate)
        if (allowReferrerList == null || allowReferrerList.size() == 0) return true;

        for(String allowReferrer : allowReferrerList) {
            boolean ret =  validateForAuthReferrer(refererHostAndPort, allowReferrer);
            if (ret) {
                return true;
            }
        }
        return false;
    }


    public String getHostAndPath(String url) {

        if (StringUtil.isNullOrEmpty(url)) return null;
        Pattern pattern = Pattern.compile("^(?i:https?://([^?]+))");
        Matcher matcher = pattern.matcher(url);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;

    }

    /**
     *  허용  Referrer을 정규식으로 변환
     *
     *  1) www.example.com 		-> www\.example\.com(/.*)?
     *  2) www.example.com/* 	-> www\.example\.com(/.*)?
     *  3) www.example.com/ 	-> www\.example\.com/?$
     *  4) *.example.com/	    	-> (.*).example\.com/?$
     *
     * @param allowReferrer
     * @return
     */
    public String toReferrerRegExp(String allowReferrer) {

        StringBuffer sb = new StringBuffer();

        sb.append(allowReferrer.replaceAll("\\.","\\\\."));

        if (allowReferrer.endsWith("/")) {
            sb.setLength(sb.length()-1);
            sb.append("/?$");
        } else if (allowReferrer.endsWith("/*")) {
            sb.setLength(sb.length()-2);
            sb.append("(/.*)?");
        } else {
            sb.append("(/.*)?");
        }

        if (allowReferrer.startsWith("*")) {
            sb.deleteCharAt(0);
            sb.insert(0, "(.*)");
        }

        return sb.toString();
    }


}
