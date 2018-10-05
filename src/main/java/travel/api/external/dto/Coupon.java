package travel.api.external.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by we on 2017. 4. 14..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coupon {
    private String orderCode;
    private String orderDate;
    private String orderName;
    private String orderMobile;
    private String couponType;
    private String companyId;
    private List<Product> products;

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "orderCode='" + orderCode + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderMobile='" + orderMobile + '\'' +
                ", couponType='" + couponType + '\'' +
                ", companyId='" + companyId + '\'' +
                ", products=" + products +
                '}';
    }
}
