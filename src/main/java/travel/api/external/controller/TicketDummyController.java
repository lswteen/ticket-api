package travel.api.external.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import travel.api.external.constants.CommonConstants;
import travel.api.external.dto.Coupon;
import travel.api.external.dto.HttpTicketRequest;
import travel.api.external.service.ExternalDummyService;
import travel.api.external.web.binder.HttpRequestBinder;
import travel.api.external.web.binder.HttpResponseBinder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by we on 2017. 5. 18..
 */
@Controller
@RequestMapping("/agency/v1/dummy")
public class TicketDummyController {

    @Inject
    @Qualifier(value="externalServiceDummyImpl")
    private ExternalDummyService externalService;

    @Autowired
    private HttpRequestBinder httpRequestBinder;

    /**
     * 주문내역조회
     * API_EIS_001
     **/
    @RequestMapping(value="/order/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> order(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable String companyId, @RequestParam String orderDate,
                                   @ModelAttribute HttpTicketRequest ticketRequest){

        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_001);
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK, externalService.orders(requestBinder));

        return responseEntity;
    }

    /**
     * 환불내역조회
     * API_EIS_002
     **/
    @RequestMapping(value="/refund/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> refund(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable String companyId, @RequestParam String refundDate,
            @RequestParam String refundDiv, @ModelAttribute HttpTicketRequest ticketRequest){

        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_002);
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK,externalService.refunds(requestBinder)
        );
        return responseEntity;
    }

    /**
     * 티켓 조회
     * API_EIS_003
     **/
    @RequestMapping(value="/{ticketCode}", method = RequestMethod.GET)
    public ResponseEntity<?> ticket(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable String ticketCode,@ModelAttribute HttpTicketRequest ticketRequest){

        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_003);
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK,externalService.ticketInfo(requestBinder)
        );
        return responseEntity;
    }

    /**
     * 정액권 사용 처리
     * API_EIS_004
     **/
    @RequestMapping(value="/used", method = RequestMethod.POST)
    public ResponseEntity<?> used(
            HttpServletRequest request, HttpServletResponse response,
            @RequestBody HttpTicketRequest ticketRequest){

        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_004);
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK, externalService.used(requestBinder)
        );
        return responseEntity;
    }

    /**
     * 단일권 사용 처리
     * API_EIS_004
     **/
    @RequestMapping(value="/used/single", method = RequestMethod.POST)
    public ResponseEntity<?> singleUsed(
            HttpServletRequest request, HttpServletResponse response,
            @RequestBody HttpTicketRequest ticketRequest){

        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_011);
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK, externalService.singleUsed(requestBinder)
        );
        return responseEntity;
    }

    /**
     * 다회권 사용 처리
     * API_EIS_004
     **/
    @RequestMapping(value="/used/multi", method = RequestMethod.POST)
    public ResponseEntity<?> multiUsed(
            HttpServletRequest request, HttpServletResponse response,
            @RequestBody HttpTicketRequest ticketRequest){

        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_012);
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK, externalService.multiUsed(requestBinder)
        );
        return responseEntity;
    }

    /**
     * 예약 처리
     * API_EIS_005
     * @param request
     * @param response
     * @param ticketRequest
     * @return
     */
    @RequestMapping(value="/reservation", method = RequestMethod.POST)
    public ResponseEntity<?> reserve(
            HttpServletRequest request, HttpServletResponse response,
            @RequestBody HttpTicketRequest ticketRequest){

        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_005);
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK, externalService.reservation(requestBinder)
        );
        return responseEntity;
    }

    /**
     * 사용 처리 취소
     * API_EIS_006
     **/
    @RequestMapping(value="/used/cancel", method= RequestMethod.POST)
    public ResponseEntity<?> usedToCancle(
            HttpServletRequest request, HttpServletResponse response,
            @RequestBody HttpTicketRequest ticketRequest){

        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_006);
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                externalService.usedToCancel(requestBinder)
        );
        return responseEntity;
    }

    /**
     * 주문 내역 처리
     * API_EIS_008
     **/
    @RequestMapping(value="/order/{ticketCode}", method= RequestMethod.POST)
    public ResponseEntity<?> order(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable String ticketCode, @RequestBody HttpTicketRequest ticketRequest){

        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_008);
        ticketRequest.setTicketCode(ticketCode);

        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                externalService.orderProcess(requestBinder)
        );
        return responseEntity;
    }

    /**
     * 위메프 --> 대행사
     * 쿠폰 발급 요청
     * API_EIS_007
     **/
    @RequestMapping(value="/coupon", method= RequestMethod.POST)
    public ResponseEntity<?> coupon(
            HttpServletRequest request, HttpServletResponse response,
            @RequestBody Coupon coupon){
        //FIXME : 해당 API는 대행사 서버정보에 정상적으로 접근이 가능한상태일때 가능함
        HttpTicketRequest ticketRequest = new HttpTicketRequest();
        ticketRequest.setApiCode(CommonConstants._APICODE.API_IIS_004);
        ticketRequest.setCoupon(coupon);
        httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK,externalService.coupon(ticketRequest));
        return responseEntity;
    }

    /**
     * 바코드 발송
     * @param ticketRequest
     * @return
     */
    @RequestMapping(value="/send", method= RequestMethod.POST)
    public ResponseEntity<?> send(@RequestBody HttpTicketRequest ticketRequest){
        ticketRequest.setApiCode(CommonConstants._APICODE.API_IIS_002);
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                externalService.send(requestBinder)
        );
        return responseEntity;
    }

    /**
     * 위메프 --> 대행사
     * 쿠폰 파기
     * API_EIS_009
     **/
    @RequestMapping(value="/coupon/discarded", method= RequestMethod.POST)
    public ResponseEntity<?> couponDiscarded(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable String companyId){
        //FIXME : 해당 API는 대행사 서버정보에 정상적으로 접근이 가능한상태일때 가능함

        HttpTicketRequest ticketRequest = new HttpTicketRequest();
        ticketRequest.setApiCode(CommonConstants._APICODE.API_IIS_003);
        ticketRequest.setCompanyId(companyId);
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);

        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                externalService.couponDiscarded(requestBinder)
        );
        return responseEntity;
    }





}
