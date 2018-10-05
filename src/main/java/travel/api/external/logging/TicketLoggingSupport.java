package travel.api.external.logging;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import travel.api.external.adaptor.AcceptData;
import travel.log.LogUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by we on 2017. 2. 24..
 */
@Component
public class TicketLoggingSupport {

    @Autowired
    private LogUtils logUtils;

    private static Logger info = LoggerFactory.getLogger(LoggingConstants.Info.class);
    private static Logger error = LoggerFactory.getLogger(LoggingConstants.Error.class);

    public void info(AcceptData acceptData) {
        long currentTime = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").
                format(new Date(System.currentTimeMillis())));
        long totalMethodTime = (System.currentTimeMillis() - acceptData.getRequestTimeMillis());
        long assetLeadTime = acceptData.getAssetLeadTime();
        long tourProcessTime = totalMethodTime - assetLeadTime;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String requestURI = (StringUtils.isNotEmpty(acceptData.getURI()))? acceptData.getURI() : "null";
        String requestHeader = (null != acceptData.getRequestHeaders()) ? acceptData.getRequestHeaders().toString() : "null";
        String requestPayload = (String) request.getAttribute("requestJson");
        HttpStatus responseStatus = (null != acceptData.getHttpStatus()) ? acceptData.getHttpStatus() : null;
        String responsePayload = (null!=  acceptData.getResponsePayload()) ? acceptData.getResponsePayload().toString() : "null";

        info.info("{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}",
                currentTime,                                                                                //로그적재시간
                acceptData.getRequestId(),                                                                  //일련번호
                acceptData.getConsumer(),                                                                   //소비자
                totalMethodTime,                                                                            //총걸린시간
                assetLeadTime,                                                                              //Asset 프로세스시간
                tourProcessTime,                                                                            //투어 티켓 프로세스시간
                acceptData.getCompanyId(),                                                                  //대행사 ID
                requestURI,                                                                                 //request URI
                requestHeader,                                                                              //request header
                requestPayload,                                                                             //request payload
                responseStatus,                                                                             //response status
                responsePayload,                                                                            //response payload
                (StringUtils.isNotEmpty(acceptData.getPayload())) ? acceptData.getPayload() : "0"           //response data size

        );
        logUtils.createDocument(acceptData,currentTime,totalMethodTime,assetLeadTime,tourProcessTime);
    }

    public void error(ResponseEntity<?> responseEntity, String e) {
        long currentTime = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").
                format(new Date(System.currentTimeMillis())));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        AcceptData acceptData = (AcceptData) request.getAttribute("data");

        long totalMethodTime = 0;
        long assetLeadTime = 0;
        long tourProcessTime = 0;
        long payloadLength = 0;
        String sendPayload = "";

        if (acceptData != null) {
            totalMethodTime = (System.currentTimeMillis() - acceptData.getRequestTimeMillis());
            assetLeadTime = acceptData.getAssetLeadTime();
            tourProcessTime = totalMethodTime - assetLeadTime;
            payloadLength = (StringUtils.isNotEmpty(acceptData.getPayload())) ? acceptData.getPayload().length() : 0;
            if(StringUtils.isNotEmpty(String.valueOf(acceptData.getSendPayload()))){
                sendPayload = String.valueOf(acceptData.getSendPayload());
            }
        }
        String requestPayload = (String) request.getAttribute("requestJson");
        error.info("{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}",
                currentTime,
                responseEntity.getStatusCode(),
                totalMethodTime,
                assetLeadTime,
                tourProcessTime,
                payloadLength,
                requestPayload,
                sendPayload,
                (payloadLength > 0) ? acceptData.getPayload() : "",
                e,
                request.getRequestURI()

        );
        logUtils.createDocument(acceptData,currentTime,totalMethodTime,assetLeadTime,tourProcessTime);

        //slack 알림
        //logUtils.sendSlackMessage(requestPayload);
    }


}