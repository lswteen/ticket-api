package travel.api.external.exception;

import travel.api.external.constants.ResponseCode;

/**
 * Created by we on 2017. 2. 24..
 */
public class TicketException extends RuntimeException{
    public TicketException(ResponseCode code){
        this.responseCode = code;
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public TicketException(ResponseCode code, Object details){
        this.responseCode = code;
        this.code = code.getCode();
        this.message = code.getMessage();
        this.details = details;
    }

    private final ResponseCode responseCode;
    private int code;
    private String message;
    private Object details;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getDetails() {
        return details;
    }
    public void setDetails(Object details) {
        this.details = details;
    }
    public ResponseCode getResponseCode() {
        return responseCode;
    }

}
