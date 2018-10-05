package travel.api.external.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import travel.api.external.domain.AbstractTransferObject;

import java.util.List;

/**
 * Created by we on 2017. 3. 15..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticket extends AbstractTransferObject{
    private Long totalCount;
    private String productId;
    private List<Order> orders;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
