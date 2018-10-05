package travel.api.Internel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by we on 2017. 4. 20..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OptionIssued {
    private String optionId;
    private Long optionCount;
    private String productId;       //상품 일련번호
    private String ticketCode;

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public Long getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(Long optionCount) {
        this.optionCount = optionCount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }
}
