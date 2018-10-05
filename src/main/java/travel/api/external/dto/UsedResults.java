package travel.api.external.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by we on 2017. 3. 15..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsedResults {
    private String ticketCode;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTicketCode() {
        return ticketCode;
    }
    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

}
