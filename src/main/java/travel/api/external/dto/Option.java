package travel.api.external.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by we on 2017. 3. 21..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Option {
    private String optionId;
    private String optionName;
    private Long optionCount;
    private Long refundCount;
    private Long amount;
    private List<TicketItem> tickets;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Long getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(Long optionCount) {
        this.optionCount = optionCount;
    }

    public List<TicketItem> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketItem> tickets) {
        this.tickets = tickets;
    }

    public Long getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Long refundCount) {
        this.refundCount = refundCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        return optionId != null ? optionId.equals(option.optionId) : option.optionId == null;
    }

    @Override
    public int hashCode() {
        return optionId != null ? optionId.hashCode() : 0;
    }
}
