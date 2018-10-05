package travel.api.external.exception;

import travel.api.external.constants.ResponseCode;
import travel.api.external.domain.AbstractTransferObject;

/**
 * Created by we on 2017. 2. 27..
 */
public class TicketApiException extends RuntimeException{
    private final ResponseCode responseCode;
    private final int code;
    private final AbstractTransferObject transfer;
    private final String message;

    public <T extends AbstractTransferObject> TicketApiException(ResponseCode code, T t) {
        this.responseCode = code;
        this.code = code.getCode();
        this.message = code.getMessage();
        this.transfer = t;
    }
    public <T extends AbstractTransferObject> TicketApiException(ResponseCode code, String message, T t) {
        this.responseCode = code;
        this.code = code.getCode();
        this.message = message;
        this.transfer = t;
    }

    public String getMessage() {
        return message;
    }
    public ResponseCode getResponseCode() {
        return responseCode;
    }
    public int getCode() {
        return code;
    }
    public AbstractTransferObject getTransfer() {
        return transfer;
    }
}
