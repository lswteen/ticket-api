package travel.security;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IPCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        int lastDot = request.getRemoteAddr().lastIndexOf(".");
        String ip = request.getRemoteAddr().substring(0,lastDot);

        String _ip = request.getHeader("HTTP_X_FORWARDED_FOR");

        if(_ip == null || _ip.length() == 0 || _ip.toLowerCase().equals("unknown"))
            _ip = request.getHeader("REMOTE_ADDR");

        if(_ip == null || _ip.length() == 0 || _ip.toLowerCase().equals("unknown"))
            _ip = request.getRemoteAddr();

        /**
         * 아이피 차단 방식은 주소 클래스 C Class (networkId.networkId.networkId.hostId) 0.0.0.0
         * networkId를 확인하고 차단
         */
        //System.out.println("--------아이피 대역------->"+ip+_ip);
        /*if(!ip.equals("222.231.17") &&
            !ip.equals("192.168.4") &&
            !ip.equals("10.102.40") &&     // 해당ip는 개발자 pc 추후에 삭제 처리
            !ip.equals("210.97.195") &&
            !ip.equals("192.168.200")){
            throw new ItemApiException(ResponseCode.ACCESS_DENIED_IP,"허가되지않은 ip : "+request.getRemoteAddr(), new AbstractTransferObject());
        }*/
        return true;
    }
}
