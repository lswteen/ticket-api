package travel.api.Internel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import travel.api.Internel.domain.HttpInternelRequest;
import travel.api.Internel.service.InternelCouponService;
import travel.api.external.constants.CommonConstants;
import travel.api.external.controller.AbstractRestController;
import travel.api.external.dto.Coupon;
import travel.api.external.web.binder.HttpRequestBinder;
import travel.api.external.web.binder.HttpResponseBinder;

import javax.servlet.http.HttpServletRequest;

import static travel.util.TicketUtil.requestToJson;

/**
 * Created by we on 2017. 4. 19..
 */
@Controller
public class InternelRestController extends AbstractRestController{

    @Autowired
    private InternelCouponService internelCouponService;

    @Autowired
    private HttpRequestBinder httpRequestBinder;

    @Autowired
    private HttpResponseBinder httpResponseBinder;

    /**
     * 위메프 내부 티켓 코드 생성
     *
     * @param httpInternelRequest
     * @return
     */
    @RequestMapping(value="/v1/coupon", method = RequestMethod.POST)
    public ResponseEntity<?> generateCoupon(@RequestBody HttpInternelRequest httpInternelRequest,
                                          HttpServletRequest request){
        requestToJson(httpInternelRequest,request);       //requetJson 적재
        httpInternelRequest.setApiCode(CommonConstants._APICODE.API_IIS_001);
        HttpInternelRequest requestBinder = httpRequestBinder.toCoupon(httpInternelRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(
                HttpStatus.OK, internelCouponService.generateCoupon(requestBinder, request));
        return responseEntity;
    }

    /**
     * 파트너(대행사) 쿠폰 발급 요청
     **/
    @RequestMapping(value="/agency/v1/coupon/{companyId}", method= RequestMethod.POST)
    public ResponseEntity<?> coupon(HttpServletRequest request,
                                    @RequestBody Coupon coupon,
                                    @PathVariable String companyId){
        requestToJson(coupon,request);
        HttpInternelRequest ticketRequest = new HttpInternelRequest();
        ticketRequest.setApiCode(CommonConstants._APICODE.API_IIS_004);
        ticketRequest.setCoupon(coupon);
        ticketRequest.setCompanyId(companyId);
        HttpInternelRequest requestBinder =  httpRequestBinder.toCoupon(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                internelCouponService.coupon(requestBinder));
        return responseEntity;
    }

    /**
     * 바코드 발송
     * @param ticketRequest
     * @return
     */
    @RequestMapping(value="/agency/v1/send/{companyId}", method = RequestMethod.POST)
    public ResponseEntity<?> send(HttpServletRequest request, @RequestBody HttpInternelRequest ticketRequest,
                                  @PathVariable String companyId){
        requestToJson(ticketRequest,request);
        ticketRequest.setApiCode(CommonConstants._APICODE.API_IIS_002);
        ticketRequest.setCompanyId(companyId);
        HttpInternelRequest requestBinder = httpRequestBinder.toCoupon(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                internelCouponService.send(requestBinder));
        return responseEntity;
    }

    /**
     * 쿠폰 파기
     **/
    @RequestMapping(value="/agency/v1/coupon/discarded/{companyId}", method= RequestMethod.POST)
    public ResponseEntity<?> couponDiscarded(HttpServletRequest request,
                                             @RequestBody HttpInternelRequest ticketRequest,
                                             @PathVariable String companyId){
        requestToJson(ticketRequest,request);
        ticketRequest.setApiCode(CommonConstants._APICODE.API_IIS_003);
        ticketRequest.setCompanyId(companyId);
        HttpInternelRequest requestBinder =  httpRequestBinder.toCoupon(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                internelCouponService.couponDiscarded(requestBinder));
        return responseEntity;
    }



}
