package travel.api.Internel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import travel.api.Internel.dto.CouponIssued;
import travel.api.external.constants.CommonConstants;
import travel.api.external.domain.AbstractTransferObject;
import travel.api.external.dto.Coupon;
import travel.api.external.dto.Option;

import java.util.List;

/**
 * Created by we on 2017. 4. 20..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpInternelRequest extends AbstractTransferObject {
    @JsonIgnore
    private CommonConstants._APICODE apiCode;        //API 구분
    @JsonIgnore
    private String companyId;                   //대행사(파트너) ID
    private String orderCode;                   //주문번호
    private String orderMobile;                 //구매자모바일
    private String orderName;                   //구매자명
    private List<CouponIssued> coupons;         //coupon
    private List<Option> options;
    private Coupon coupon;
    @JsonIgnore
    private String requestJson;

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public String getCompanyId() {return companyId;}
    public void setCompanyId(String companyId) {this.companyId = companyId;}
    public Coupon getCoupon() {return coupon;}
    public void setCoupon(Coupon coupon) {this.coupon = coupon;}
    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    public List<Option> getOptions() {
        return options;
    }
    public void setOptions(List<Option> options) {
        this.options = options;
    }
    public CommonConstants._APICODE getApiCode() {
        return apiCode;
    }
    public void setApiCode(CommonConstants._APICODE apiCode) {
        this.apiCode = apiCode;
    }
    public String getOrderCode() {
        return orderCode;
    }
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
    public String getOrderMobile() {
        return orderMobile;
    }
    public void setOrderMobile(String orderMobile) {
        this.orderMobile = orderMobile;
    }
    public List<CouponIssued> getCoupons() {
        return coupons;
    }
    public void setCoupons(List<CouponIssued> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "HttpInternelRequest{" +
                "apiCode=" + apiCode +
                ", companyId='" + companyId + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", orderMobile='" + orderMobile + '\'' +
                ", orderName='" + orderName + '\'' +
                ", coupons=" + coupons +
                ", options=" + options +
                ", coupon=" + coupon +
                '}';
    }
}
