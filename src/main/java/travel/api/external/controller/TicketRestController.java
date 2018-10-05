package travel.api.external.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import travel.api.external.constants.CommonConstants;
import travel.api.external.dto.HttpTicketRequest;
import travel.api.external.service.ExternalService;
import travel.api.external.web.binder.HttpRequestBinder;
import travel.api.external.web.binder.HttpResponseBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static travel.util.TicketUtil.requestToJson;

/**
 * ticket Test RestController
 */
@Controller
@RequestMapping("/agency/v1")
@Api(description = "Ticket API")
public class TicketRestController extends AbstractRestController{

    @Autowired
    @Qualifier(value="externalServiceImpl")
    private ExternalService externalService;

    @Autowired
    private HttpRequestBinder httpRequestBinder;

    @Autowired
    private HttpResponseBinder httpResponseBinder;

    /**
     * 주문내역조회
     * API_EIS_001
     **/
    @RequestMapping(value="/order/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<?> order(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable String companyId, @RequestParam String orderDate,
                                   @ModelAttribute HttpTicketRequest ticketRequest){
        requestToJson(ticketRequest,request);       //requetJson 적재
        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_001);
        ticketRequest.setCompanyId(String.valueOf(request.getAttribute("companyId")));
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                externalService.orders(requestBinder));
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
        requestToJson(ticketRequest,request);       //requetJson 적재
        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_002);
        ticketRequest.setCompanyId(String.valueOf(request.getAttribute("companyId")));
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                externalService.refunds(requestBinder));

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
        requestToJson(ticketRequest,request);       //requetJson 적재
        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_003);
        ticketRequest.setCompanyId(String.valueOf(request.getAttribute("companyId")));
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                externalService.ticketInfo(requestBinder));

        return responseEntity;
    }

    /**
     * 금액권 사용 처리
     * API_EIS_004
     **/
    @RequestMapping(value="/used", method = RequestMethod.POST)
    public ResponseEntity<?> used(
            HttpServletRequest request, @RequestBody HttpTicketRequest ticketRequest){
        requestToJson(ticketRequest,request);
        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_004);
        ticketRequest.setCompanyId(String.valueOf(request.getAttribute("companyId")));
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                externalService.used(requestBinder));
        return responseEntity;
    }

    /**
     * 단일권 사용 처리
     * API_EIS_004
     **/
    @RequestMapping(value="/used/single", method = RequestMethod.POST)
    public ResponseEntity<?> singleUsed(
            HttpServletRequest request, @RequestBody HttpTicketRequest ticketRequest){
        requestToJson(ticketRequest,request);
        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_011);
        ticketRequest.setCompanyId(String.valueOf(request.getAttribute("companyId")));
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                externalService.singleUsed(requestBinder));

        return responseEntity;
    }

    /**
     * 다회권 사용 처리
     * API_EIS_004
     **/
    @RequestMapping(value="/used/multi", method = RequestMethod.POST)
    public ResponseEntity<?> multiUsed(
            HttpServletRequest request, @RequestBody HttpTicketRequest ticketRequest){
        requestToJson(ticketRequest,request);
        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_012);
        ticketRequest.setCompanyId(String.valueOf(request.getAttribute("companyId")));
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                externalService.multiUsed(requestBinder));

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
        requestToJson(ticketRequest,request);       //requetJson 적재
        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_005);
        ticketRequest.setCompanyId(String.valueOf(request.getAttribute("companyId")));
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                externalService.reservation(requestBinder));

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
        requestToJson(ticketRequest,request);       //requetJson 적재
        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_006);
        ticketRequest.setCompanyId(String.valueOf(request.getAttribute("companyId")));
        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
                externalService.usedToCancel(requestBinder));
        return responseEntity;
    }

    /**
     * 주문 매핑 조회
     * @param request
     * @param orderCode
     * @return
     */
//    @RequestMapping(value="/order/mapping/{orderCode}", method= RequestMethod.GET)
//    public ResponseEntity<?> orderMapping(
//            HttpServletRequest request, @PathVariable String orderCode
//    ,@ModelAttribute HttpTicketRequest ticketRequest){
//        requestToJson(ticketRequest,request);       //requetJson 적재
//        ticketRequest.setApiCode(CommonConstants._APICODE.API_EIS_010);
//        ticketRequest.setCompanyId(String.valueOf(request.getAttribute("companyId")));
//        ticketRequest.setOrderCode(orderCode);
//        HttpTicketRequest requestBinder =  httpRequestBinder.toTicket(ticketRequest);
//        ResponseEntity<?> responseEntity = httpResponseBinder.toResponseEntity(HttpStatus.OK,
//                externalService.orderMapping(requestBinder));
//        return responseEntity;
//    }


}
