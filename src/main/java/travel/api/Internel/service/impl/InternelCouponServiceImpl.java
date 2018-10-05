package travel.api.Internel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import travel.api.Internel.domain.HttpInternelRequest;
import travel.api.Internel.dto.CouponIssued;
import travel.api.Internel.dto.OptionIssued;
import travel.api.Internel.service.InternelCouponService;
import travel.api.auth.dao.AuthDao;
import travel.api.auth.dto.AuthKey;
import travel.api.external.adaptor.AcceptData;
import travel.api.external.adaptor.Adaptor;
import travel.api.external.constants.ResponseCode;
import travel.api.external.domain.AbstractTransferObject;
import travel.api.external.exception.TicketApiException;
import travel.util.TicketUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by we on 2017. 4. 19..
 */
@Service
public class InternelCouponServiceImpl implements InternelCouponService {

    @Autowired
    private Adaptor adaptor;

    @Autowired
    private AuthDao authDao;

    private AuthKey getAuthInfo(HttpInternelRequest request) {
        AuthKey isResult = authDao.selectSouthBoundUrl(request.getCompanyId());
        if(null == isResult){
            throw new TicketApiException(ResponseCode.NotFound,new AbstractTransferObject());
        }
        return isResult;
    }

    private List<OptionIssued> ticket(String codeType, String productId,
                                      String optionId, Long optionCount,
                                      String orderCode, String orderMobile){
        List<OptionIssued> optionIssueds = new ArrayList<OptionIssued>();
        for (int i = 0; i < optionCount; i++){
            String isTicket = "";
            switch (codeType) {
                case "BAR":
                    isTicket = TicketUtil.getStringTicket(orderCode, orderMobile);
                    break;
                case "TXT":
                    isTicket = TicketUtil.getStringTicket(orderCode, orderMobile);
                    break;
                case "QR":
                    isTicket = TicketUtil.getNumberTicket(orderCode, orderMobile);
                    break;
            }
            OptionIssued optionIssued = new OptionIssued();
            optionIssued.setOptionId(optionId);
            optionIssued.setProductId(productId);
            optionIssued.setTicketCode(isTicket);
            optionIssueds.add(optionIssued);
        }
        return optionIssueds;
    }


    @Override
    public List<OptionIssued> generateCoupon(HttpInternelRequest httpInternelRequest, HttpServletRequest request) {
        List<OptionIssued> couponList = new ArrayList<OptionIssued>();

        List<CouponIssued> couponIssueds = httpInternelRequest.getCoupons();
        //쿠폰요청 순환문
        for (CouponIssued item: couponIssueds) {
            List<OptionIssued> optionIssueds =  item.getOptions();

            //옵션별 순환문
            for(OptionIssued optionIssuedItem : optionIssueds){
                //문자 발송여부 판단후 Y라면 sendMessage 호출
                List<OptionIssued> tickets = ticket(
                        item.getTicketCodeType(), item.getProductId(),
                        optionIssuedItem.getOptionId(), optionIssuedItem.getOptionCount(),
                        httpInternelRequest.getOrderCode(), httpInternelRequest.getOrderMobile()
                );
                //옵션카운트만큼 티켓코드 생성 후 리스트에 addAll
                couponList.addAll(tickets);
            }
        }
        //발급된 쿠폰 리스트를 response 전달
        AcceptData acceptData = new AcceptData();
        acceptData.setCompanyId("payment");
        acceptData.setConsumer("API_IIS_001");
        acceptData.setHttpStatus(HttpStatus.OK);
        acceptData.setPayload(couponList.toString());
        request.setAttribute("data",acceptData);
        return couponList;
    }

    @Override
    public AcceptData send(HttpInternelRequest request) {
        AcceptData acceptData = new AcceptData();
        AuthKey isResult = getAuthInfo(request);
        acceptData.setRestKey(isResult.getApiKey());
        acceptData.setURI(isResult.getSouthBoundUrl().concat("/ticket/agency/v1/send"));
        //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/send");
        acceptData.setRequestMethod("POST");
        acceptData.setConsumer("API_IIS_002");
        acceptData.setRequestPayload(request);
        ResponseEntity<String> responseEntity = adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        acceptData.setPayload(responseEntity.getBody());
        return acceptData;
    }

    @Override
    public AcceptData couponDiscarded(HttpInternelRequest request) {
        AcceptData acceptData = new AcceptData();
        AuthKey isResult = getAuthInfo(request);
        acceptData.setRestKey(isResult.getApiKey());
        acceptData.setURI(isResult.getSouthBoundUrl().concat("/ticket/agency/v1/coupon/discarded"));
        //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/coupon/discarded/123");
        acceptData.setRequestMethod("POST");
        acceptData.setConsumer("API_IIS_003");
        request.setCompanyId(null);
        acceptData.setRequestPayload(request);
        ResponseEntity<String> responseEntity = adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        acceptData.setPayload(responseEntity.getBody());
        return acceptData;
    }

    @Override
    public AcceptData coupon(HttpInternelRequest request) {
        AcceptData acceptData = new AcceptData();
        AuthKey isResult = getAuthInfo(request);
        acceptData.setURI(isResult.getSouthBoundUrl().concat("/ticket/agency/v1/coupon"));
        //acceptData.setURI("http://localhost:8080/ticket/agency/v1/dummy/coupon");
        acceptData.setRestKey(isResult.getApiKey());
        acceptData.setRequestMethod("POST");
        acceptData.setConsumer("API_IIS_004");
        acceptData.setRequestPayload(request.getCoupon());
        acceptData.setSouthBoundToken(isResult.getSouthBoundToken());
        ResponseEntity<String> responseEntity = adaptor.execute(acceptData);
        acceptData.setHttpStatus(responseEntity.getStatusCode());
        acceptData.setPayload(responseEntity.getBody());
        return acceptData;
    }



}
