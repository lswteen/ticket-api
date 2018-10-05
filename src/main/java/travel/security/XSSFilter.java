package travel.security;

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class XSSFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        XSSRequestWrapper wrappedRequest = new XSSRequestWrapper((HttpServletRequest) request);
        // 바디 이외에 담긴 파라미터 XSS 처리 (GET,POST)
        if(request.getParameterNames().hasMoreElements()) {
            List<String> params = Collections.list(request.getParameterNames());
            for (int i = 0; i < params.size(); i++) {
                wrappedRequest.getParameter(params.get(i));
            }
        }
        //System.out.println(wrappedRequest.getParameter("name") + wrappedRequest.getParameter("age"));

        String body = IOUtils.toString(wrappedRequest.getReader());
        // 바디에 담긴 스트링에 특수문자 치환하고 리퀘스트를 다시 만들어 반환... " 더블 쿼테이션은 다시 변환...
        if(!"".equals(body))
        {
            //System.out.println("before-------->"+body);
            body = XSSUtils.stripXSS(body);
            //System.out.println("after--------->"+body);
            body = body.replaceAll("&quot;","\"");
            wrappedRequest.resetInputStream(body.getBytes("UTF-8"));
        }
        chain.doFilter(wrappedRequest, response);
    }
}