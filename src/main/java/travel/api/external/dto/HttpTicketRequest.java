package travel.api.external.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import travel.api.external.constants.CommonConstants;
import travel.api.external.domain.AbstractTransferObject;

import java.util.List;

/**
 * Created by we on 2017. 3. 14..
 * 티켓외부연동 Request
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpTicketRequest extends AbstractTransferObject {

    @JsonIgnore
    private CommonConstants._APICODE apiCode;        //API 구분
    @JsonIgnore
    private String companyId;       //파트너 일련번호

    private String productId;       //상품 일련번호
    private String orderDate;       //주문날짜
    private String orderTime;       //주문시간 (00~23) 01 입력시  (00:30 ~ 01:00 ~ 01:30)
    private String refundDate;      //반품날짜
    private String refundDiv;       //반품상태
    private String ticketType;      //외부 EXT, 내부 INT
    private Long amount;            //취소금액
    private String orderCode;
    private String orderName;
    private String orderMobile;
    private Coupon coupon;

    private String pinNo;           //외부업체에서 발급된 상품 번
    private String ticketCode;      //외부 (업체에서 발행된 pinNo), 내부( 위메프에서 발행)
    private String authCode;        //티켓 인증 코드
    //@JsonDeserialize(using = CustomStringDeserializer.class)
    private List<TicketItem> tickets;        //티켓N건
    private String requestJson;

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public String getPinNo() {
        return pinNo;
    }

    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }


    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderMobile() {
        return orderMobile;
    }

    public void setOrderMobile(String orderMobile) {
        this.orderMobile = orderMobile;
    }

    public CommonConstants._APICODE getApiCode() {
        return apiCode;
    }

    public void setApiCode(CommonConstants._APICODE apiCode) {
        this.apiCode = apiCode;
    }

    public String getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }

    public String getRefundDiv() {
        return refundDiv;
    }

    public void setRefundDiv(String refundDiv) {
        this.refundDiv = refundDiv;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public List<TicketItem> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketItem> tickets) {
        this.tickets = tickets;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        return "HttpTicketRequest{" +
                "apiCode=" + apiCode +
                ", companyId='" + companyId + '\'' +
                ", productId='" + productId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", refundDate='" + refundDate + '\'' +
                ", refundDiv='" + refundDiv + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", amount=" + amount +
                ", orderCode='" + orderCode + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderMobile='" + orderMobile + '\'' +
                ", coupon=" + coupon +
                ", pinNo='" + pinNo + '\'' +
                ", ticketCode='" + ticketCode + '\'' +
                ", authCode='" + authCode + '\'' +
                ", tickets=" + tickets +
                ", requestJson='" + requestJson + '\'' +
                '}';
    }
}
