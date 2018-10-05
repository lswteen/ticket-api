package travel.api.external.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import travel.api.external.constants.ResponseCode;

/**
 * Created by we on 2017. 2. 24..
 */
public class TicketResponse<T> {

    public <I extends AbstractTransferObject> TicketResponse(ResponseCode responseCode, I transfer) {
        this.responseCode = responseCode;
        this.code = responseCode.getCode();
        this.body = (T) responseCode.getMessage();
        this.transfer = transfer;
    }

    public <I extends AbstractTransferObject> TicketResponse(ResponseCode responseCode, T body, I transfer) {
        this.responseCode = responseCode;
        this.code = responseCode.getCode();
        this.body = body;
        this.transfer = transfer;
    }

    private final int code;
    private final T body;

    @JsonIgnore
    private final ResponseCode responseCode;
    @JsonIgnore
    private final AbstractTransferObject transfer;

    public int getCode() {
        return code;
    }
    public T getBody() {
        return body;
    }
    public ResponseCode getResponseCode() {
        return responseCode;
    }
    public AbstractTransferObject getTransfer() {
        return transfer;
    }
}
