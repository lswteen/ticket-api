package travel.api.external.adaptor.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import travel.api.external.adaptor.AcceptData;
import travel.api.external.adaptor.Adaptor;
import travel.commons.utility.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

/**
 * Created by we on 2017. 5. 8..
 */
@Service
public class CommonAdaptorImpl implements Adaptor{

    @Override
    public ResponseEntity<String> execute(AcceptData acceptdata) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.set("requestId",acceptdata.getRequestId());
        if(StringUtils.isNotEmpty(acceptdata.getRestKey())){
            headers.set("restKey", acceptdata.getRestKey());
        }
        if(StringUtils.isNotEmpty(acceptdata.getSouthBoundToken())){
            headers.set("Authorization",acceptdata.getSouthBoundToken());
        }
        HttpMethod httpMethod = HttpMethod.valueOf(acceptdata.getRequestMethod());

        HttpEntity<Object> httpEntity = null;
        if( httpMethod == HttpMethod.POST || httpMethod == HttpMethod.PUT){
            httpEntity = new HttpEntity<Object>(acceptdata.getRequestPayload(),headers);
            acceptdata.setSendPayload(acceptdata.getRequestPayload());
        }else{
            httpEntity = new HttpEntity<Object>(headers);
        }
        acceptdata.setRequestHeaders(headers.toSingleValueMap()); //header Map
        acceptdata.setAssetLeadTime(System.currentTimeMillis());
        ResponseEntity<String> isResult = null;
        try{
          ObjectMapper om = new ObjectMapper();
          System.out.println("URI : ".concat(acceptdata.getURI()));
          System.out.println("header : ".concat(headers.toString()));
          System.out.println("REQUEST PAYLOAD : ".concat( om.writeValueAsString(httpEntity.getBody())));

          isResult = restTemplate.exchange(acceptdata.getURI(),httpMethod,httpEntity,String.class);

          acceptdata.setHttpStatus(isResult.getStatusCode());
          System.out.println("Response status : ".concat(isResult.getStatusCode().toString()));
          if(StringUtils.isNotEmpty(isResult.getBody())){
          System.out.println("Response Payload : ".concat(isResult.getBody()));
              acceptdata.setResponsePayload(isResult.getBody());
          }else{
              acceptdata.setResponsePayload(null);
          }
        }catch (Exception e){
          e.printStackTrace();
        }
        acceptdata.setAssetLeadTime((System.currentTimeMillis() - acceptdata.getAssetLeadTime()));
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        httpServletRequest.setAttribute("data",acceptdata);
        return isResult;
    }

    @Override
    public void executeSlack(AcceptData acceptdata) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("context-Type","application/x-www-form-urlencoded");
//        headers.set("Accept","*/*");
//        HttpMethod httpMethod = HttpMethod.valueOf(acceptdata.getRequestMethod());
//        HttpEntity<Object> httpEntity = null;
//        httpEntity = new HttpEntity<Object>(acceptdata.getRequestPayload(),headers);
//        ResponseEntity<String> isResult = null;
//        try{
//            isResult = restTemplate.exchange(acceptdata.getURI(),httpMethod,httpEntity,String.class);
//            System.out.println("responseStatus : ".concat(String.valueOf(isResult.getStatusCode())));
//            System.out.println("responseBody : ".concat(isResult.getBody()));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

}
