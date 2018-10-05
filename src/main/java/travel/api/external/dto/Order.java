package travel.api.external.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by we on 2017. 3. 15..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    private String orderCode;
    private String orderDate;

    private String refundApplyDate;
    private String refundCompleteDate;
    private String orderName;
    private String orderMobile;
    private List<Product> products;

    private Long refundCount;

    //    private String optionId;
//    private String optionName;
//    private Long orderCount;


    public String getRefundApplyDate() {
        return refundApplyDate;
    }

    public void setRefundApplyDate(String refundApplyDate) {
        this.refundApplyDate = refundApplyDate;
    }

    public String getRefundCompleteDate() {
        return refundCompleteDate;
    }

    public void setRefundCompleteDate(String refundCompleteDate) {
        this.refundCompleteDate = refundCompleteDate;
    }

    public Long getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Long refundCount) {
        this.refundCount = refundCount;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
