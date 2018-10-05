package travel.api.external.domain;

import travel.api.external.execution.ErrorDetails;

/**
 * Created by we on 2017. 2. 24..
 */
public class ErrorResponse {
    private ErrorDetails error;

    public ErrorResponse() {
    }

    public ErrorResponse(ErrorDetails error) {
        this.error = error;
    }

    public ErrorDetails getError() {
        return error;
    }

    public void setError(ErrorDetails error) {
        this.error = error;
    }

}
