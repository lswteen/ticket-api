package travel.api.external.web.binder;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import travel.api.Internel.dao.InternelDao;
import travel.api.Internel.domain.HttpInternelRequest;
import travel.api.Internel.dto.CouponIssued;
import travel.api.auth.domain.HttpAuthRequest;
import travel.api.external.constants.ResponseCode;
import travel.api.external.domain.AbstractTransferObject;
import travel.api.external.dto.HttpTicketRequest;
import travel.api.external.dto.Option;
import travel.api.external.dto.Product;
import travel.api.external.dto.TicketItem;
import travel.api.external.exception.TicketApiException;
import travel.api.external.utils.ValidationUtils;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by we on 2017. 3. 14..
 */
@Component
public class HttpRequestBinder {

    @Autowired
    private InternelDao internelDao;

    private static Logger log = LoggerFactory.getLogger(HttpRequestBinder.class);
    /**
     * 티켓 Request binder
     * @param request
     * @return
     */
    public HttpTicketRequest toTicket(HttpTicketRequest request) {

        //header 체크
        switch (request.getApiCode()) {
            case API_EIS_001:
                ValidationUtils.mandatoryCheck(request.getCompanyId(),
                        request.getOrderDate());
                ValidationUtils.chkVaildSize("companyId", request.getCompanyId(),20,request);
                ValidationUtils.isThisDateValid("orderDate", request.getOrderDate(),"yyyyMMdd",request);
                if(true == StringUtils.isNotEmpty(request.getOrderTime()) ){
                    ValidationUtils.chkOrderTime("orderTime", request.getOrderTime(),request);
                }
                break;
            case API_EIS_002:
                ValidationUtils.mandatoryCheck(request.getCompanyId(),
                        request.getRefundDate(),request.getRefundDiv());
                ValidationUtils.isThisDateValid("refundDate", request.getRefundDate(),
                        "yyyyMMdd",request);
                break;
            case API_EIS_003:
                ValidationUtils.mandatoryCheck(request.getTicketCode());
                ValidationUtils.chkVaildSize("ticketCode", request.getTicketCode(),20,request);
                break;
            case API_EIS_004:
                ValidationUtils.chkSize(request.getTickets());
                for (TicketItem item : request.getTickets()) {
                    ValidationUtils.mandatoryCheck(item.getTicketCode());
                    ValidationUtils.mandatoryCheck(item.getAmount());
                }
                break;
            case API_EIS_005:
                ValidationUtils.chkSize(request.getTickets());
                break;
            case API_EIS_006:
                ValidationUtils.mandatoryCheck(request.getTicketCode());
                break;
            case API_EIS_008:
                ValidationUtils.mandatoryCheck(request.getTicketCode());
                break;
            case API_EIS_010:
                ValidationUtils.mandatoryCheck(request.getOrderCode());
                break;
            case API_EIS_011:
                ValidationUtils.chkSize(request.getTickets());
                for (TicketItem item : request.getTickets()) {
                    ValidationUtils.mandatoryCheck(item.getTicketCode());
                }
                break;
            case API_EIS_012:
                ValidationUtils.chkSize(request.getTickets());
                for (TicketItem item : request.getTickets()) {
                    ValidationUtils.mandatoryCheck(item.getTicketCode());
                    ValidationUtils.mandatoryCheck(item.getAmount());
                }
                break;
        }
        log.debug("TICKET TIS API ID : {} | HttpRequestBinder : {}",request.getApiCode(),request.toString());
        return request;
    }

    /**
     * 인증 Request binder
     * @param request
     * @return
     */
    public static HttpAuthRequest toAuth(HttpAuthRequest request) {
        switch (request.getApiCode()) {
            case API_AUTH_001:
                break;
            case API_AUTH_002:
                ValidationUtils.mandatoryCheck(request.getCompanyId(), request.getCompanyName(), request.getApiKeyType());
//                ValidationUtils.isPatternValid("companyId","^[a-zA-Z0-9]*$", request.getCompanyId());
//                ValidationUtils.isPatternValid("companyName","^[a-zA-Z0-9가-힣]*$", request.getCompanyName());
                ValidationUtils.isPatternValid("apiKeyType","^[a-zA-Z]*$", request.getApiKeyType());
                break;
            case API_AUTH_003:
                ValidationUtils.mandatoryCheck(String.valueOf(request.getApiKeyId()), request.getApiKeyType());
                ValidationUtils.isPatternValid("apiKeyId","^[0-9]*$", String.valueOf(request.getApiKeyId()));
                ValidationUtils.isPatternValid("apiKeyType","^[a-zA-Z]*$", request.getApiKeyType());
                if(StringUtils.isNotEmpty(request.getAllowIp())){
                    ValidationUtils.isPatternValid("allowIp","^[0-9.|]*$", request.getAllowIp());
                }
                if(StringUtils.isNotEmpty(request.getScope())){
                    ValidationUtils.isPatternValid("scope","^[a-zA-Z.|]*$", request.getScope());
                }
                break;
            case API_AUTH_004:
                break;
            case API_AUTH_005:
                break;
            case API_AUTH_006:
                break;
        }
        log.debug("AUTH API ID : {} | HttpRequestBinder : {}",request.getApiCode(),request.toString());
        return request;
    }

    /**
     * 내부티켓
     * @param request
     * @return
     */
    public HttpInternelRequest toCoupon(HttpInternelRequest request) {
        switch (request.getApiCode()) {
            case API_IIS_001:           //4.1.1.8 쿠폰발급
                break;
            case API_IIS_002:           //4.1.1.10 바코드 발급
                ValidationUtils.mandatoryCheck(
                        request.getOrderCode(),
                        request.getOrderName(),
                        request.getOrderMobile(),
                        request.getCompanyId()
                );
//                if(null != request.getOptions()){
//                    List<Option> newOptions = request.getOptions().stream().map(o -> {
//                        Option option = new Option();
//                        option.setOptionId(internelDao.getCompanyOption(o.getOptionId()));
//                        return option;
//                    }).collect(toList());
//                    request.setOptions(newOptions);
//                }
                break;
            case API_IIS_003:       //4.1.1.9 쿠폰파기
                ValidationUtils.mandatoryCheck(request.getCompanyId());
//                if(null != request.getCoupons().get(0).getOptionId()){
//                    List<CouponIssued> newCoupons = request.getCoupons().stream().map(c -> {
//                        CouponIssued couponIssued = new CouponIssued();
//                        couponIssued.setOptionId(internelDao.getCompanyOption(c.getOptionId()));
//                        couponIssued.setPinNo(c.getPinNo());
//                        return couponIssued;
//                    }).collect(toList());
//                    request.setCoupons(newCoupons);
//                }
                break;
            case API_IIS_004:
                ValidationUtils.mandatoryCheck(request.getCoupon().getOrderCode(),
                        request.getCoupon().getOrderDate(), request.getCoupon().getOrderName()
                        ,request.getCoupon().getOrderMobile(), request.getCompanyId());
//                if(null != request.getCoupon().getProducts()){
//                    request = filterOption(request);
//                }
                break;
        }
        log.debug("TICKET IIS API ID : {} | HttpRequestBinder : {}",request.getApiCode(),request.toString());
        return request;
    }

//    private HttpInternelRequest filterOption(HttpInternelRequest request) {
//        //Request 상품 객체
//        List<Product> products = request.getCoupon().getProducts();
//        //상품 에 있는 하위 노드를 1차원 Set Collection
//        Set<Option> optionSet = products.stream()
//                .flatMap(e -> e.getOptions().stream())
//                .collect(toSet());
//        Map<String,String> optionMap = new HashMap();
//        //옵션 Set을 Map 으로 변경 <oldValue,newValue>
//        optionSet.forEach(
//            o -> {
//                String companyCode = internelDao.getCompanyOption(o.getOptionId());
//                if(StringUtils.isNotEmpty(companyCode)){
//                    optionMap.put(o.getOptionId(),companyCode);
//                }else{
//                    throw new TicketApiException(ResponseCode.BAD_REQUEST,"대행사 옵션이 존재하지 않습니다",new AbstractTransferObject());
//                }
//            }
//        );
//        //요청 Request에 옵션을 변경
//        products.stream()
//                .forEach(p -> {
//                    List<Option> newOptionList = new ArrayList<>();
//                    p.getOptions().forEach(o -> {
//                        Option newOption = new Option();
//                        newOption.setOptionId(optionMap.get(o.getOptionId()));
//                        newOption.setAmount(o.getAmount());
//                        newOption.setOptionCount(o.getOptionCount());
//                        newOptionList.add(newOption);
//                    });
//                    p.setOptions(newOptionList);
//                });
//        return request;
//    }



}
