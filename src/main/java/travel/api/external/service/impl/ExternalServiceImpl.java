package travel.api.external.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import travel.api.external.adaptor.AcceptData;
import travel.api.external.adaptor.Adaptor;
import travel.api.external.dto.HttpTicketRequest;
import travel.api.external.service.ExternalService;
import travel.util.URIUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by we on 2017. 3. 16..
 */
@Service
public class ExternalServiceImpl implements ExternalService {

    @Autowired
    private Adaptor adaptor;

    @Autowired
    private URIUtils uriUtils;

    @Override
    public AcceptData orders(HttpTicketRequest request) {
        AcceptData acceptData = new AcceptData();
        String pathParametas = "/payment/ticket/agency/order/".concat(request.getCompanyId());
        Map queryString = new HashMap();
        queryString.put("orderDate",request.getOrderDate());

        if(StringUtils.isNotEmpty(request.getOrderTime())){
            queryString.put("orderTime",request.getOrderTime());
        }
        acceptData.setURI(uriUtils.createURI(queryString,pathParametas));
        //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/order/1111?orderDate=20170807");
        acceptData.setRequestMethod("GET");
        acceptData.setConsumer("API_EIS_001");
        ResponseEntity<String> responseEntity =  adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        if(StringUtils.isNotEmpty(responseEntity.getBody())){
            acceptData.setPayload(responseEntity.getBody());
        }
        return acceptData;
    }

    @Override
    public AcceptData refunds(HttpTicketRequest request) {
        AcceptData acceptData = new AcceptData();
        String pathParametas = "/payment/ticket/agency/refund/".concat(request.getCompanyId());
        Map queryString = new HashMap();
        queryString.put("refundDate",request.getRefundDate());
        queryString.put("refundDiv",request.getRefundDiv());
        acceptData.setURI(uriUtils.createURI(queryString,pathParametas));
        //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/refund/1111?refundDate=20170807&refundDiv=both");
        acceptData.setRequestMethod("GET");
        acceptData.setConsumer("API_EIS_002");
        ResponseEntity<String> responseEntity =  adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        if(StringUtils.isNotEmpty(responseEntity.getBody())){
            acceptData.setPayload(responseEntity.getBody());
        }
        return acceptData;
    }

    @Override
    public AcceptData ticketInfo(HttpTicketRequest request) {
        AcceptData acceptData = new AcceptData();
        String pathParametas = "/payment/ticket/agency/".concat(request.getCompanyId()).concat("/")
                .concat(request.getTicketCode());
        acceptData.setURI(uriUtils.createURI(pathParametas));
        //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/1111");
        acceptData.setRequestMethod("GET");
        acceptData.setConsumer("API_EIS_003");
        ResponseEntity<String> responseEntity =  adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        if(StringUtils.isNotEmpty(responseEntity.getBody())){
            acceptData.setPayload(responseEntity.getBody());
        }
        return acceptData;
    }

    @Override
    public AcceptData used(HttpTicketRequest request) {
        AcceptData acceptData = new AcceptData();
        String pathParametas = "/payment/ticket/agency/used/".concat(request.getCompanyId());
        acceptData.setURI(uriUtils.createURI(pathParametas));
        //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/used");
        acceptData.setRequestMethod("POST");
        acceptData.setConsumer("API_EIS_004");
        acceptData.setRequestPayload(request);
        ResponseEntity<String> responseEntity =  adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        if(StringUtils.isNotEmpty(responseEntity.getBody())){
            acceptData.setPayload(responseEntity.getBody());
        }
        return acceptData;
    }

    @Override
    public AcceptData singleUsed(HttpTicketRequest request) {
        AcceptData acceptData = new AcceptData();
        String pathParametas = "/payment/ticket/agency/used/single/".concat(request.getCompanyId());
        acceptData.setURI(uriUtils.createURI(pathParametas));
        //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/used");
        acceptData.setRequestMethod("POST");
        acceptData.setConsumer("API_EIS_011");
        acceptData.setRequestPayload(request);
        ResponseEntity<String> responseEntity =  adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        if(StringUtils.isNotEmpty(responseEntity.getBody())){
            acceptData.setPayload(responseEntity.getBody());
        }
        return acceptData;
    }

    @Override
    public AcceptData multiUsed(HttpTicketRequest request) {
        AcceptData acceptData = new AcceptData();
        String pathParametas = "/payment/ticket/agency/used/multi/".concat(request.getCompanyId());
        acceptData.setURI(uriUtils.createURI(pathParametas));
        //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/used");
        acceptData.setRequestMethod("POST");
        acceptData.setConsumer("API_EIS_012");
        acceptData.setRequestPayload(request);
        ResponseEntity<String> responseEntity =  adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        if(StringUtils.isNotEmpty(responseEntity.getBody())){
            acceptData.setPayload(responseEntity.getBody());
        }
        return acceptData;
    }

    @Override
    public AcceptData reservation(HttpTicketRequest request) {
        AcceptData acceptData = new AcceptData();
        String pathParametas = "/payment/ticket/agency/reservation/".concat(request.getCompanyId());
        acceptData.setURI(uriUtils.createURI(pathParametas));
        acceptData.setRequestMethod("POST");
        acceptData.setConsumer("API_EIS_005");
        acceptData.setRequestPayload(request);
        ResponseEntity<String> responseEntity =  adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        if(StringUtils.isNotEmpty(responseEntity.getBody())){
            acceptData.setPayload(responseEntity.getBody());
        }
        return acceptData;
    }

    @Override
    public AcceptData usedToCancel(HttpTicketRequest request) {
        AcceptData acceptData = new AcceptData();
        String pathParametas = "/payment/ticket/agency/used/cancel/".concat(request.getCompanyId());
        acceptData.setURI(uriUtils.createURI(pathParametas));
        //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/used/cancel");
        acceptData.setRequestMethod("POST");
        acceptData.setConsumer("API_EIS_006");
        acceptData.setRequestPayload(request);
        ResponseEntity<String> responseEntity =  adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        if(StringUtils.isNotEmpty(responseEntity.getBody())){
            acceptData.setPayload(responseEntity.getBody());
        }
        return acceptData;
    }

//    @Override
//    public AcceptData orderMapping(HttpTicketRequest request) {
//        AcceptData acceptData = new AcceptData();
//        acceptData.setURI(uriUtils.createURI("/ticket/agency/v1/order/".concat(request.getOrderCode())));
//       //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/order/mapping/123");
//        acceptData.setRequestMethod("GET");
//        acceptData.setConsumer("API_EIS_010");
//        ResponseEntity<String> responseEntity = adaptor.execute(acceptData);
//        acceptData.setHttpStatus(responseEntity.getStatusCode());
//        if(StringUtils.isNotEmpty(responseEntity.getBody())){
//            acceptData.setPayload(responseEntity.getBody());
//        }
//        return acceptData;
//    }

}
