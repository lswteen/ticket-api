package travel.config.exception;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * travel.config.exception
 *    |_ ErrorMessage.java
 * @brief
 * @author 어드민/박종성
 * @version 1.0
 * @date 생성 : 2014. 12. 15.
 * @date 최종수정 : 2014. 12. 15.
 */
public class ErrorMessage {
 
    private List<String> errors;
    private String errorCode;
 
    public ErrorMessage() {
    }
 
    public ErrorMessage(List<String> errors) {
        this.errors = errors;
    }
    
    public ErrorMessage(List<String> errors, String errorCode) {
        this.errors = errors;
        this.errorCode = errorCode;
    }
 
    public ErrorMessage(String error) {
        this(Collections.singletonList(error));
    }
    
    public ErrorMessage(String error, String errorCode) {
        this(Collections.singletonList(error));
        this.errorCode = errorCode;
    }
 
    public ErrorMessage(String ... errors) {
        this(Arrays.asList(errors));
    }
    
    public ErrorMessage(String errorCode, String ... errors) {
        this(Arrays.asList(errors));
        this.errorCode = errorCode;
    }
 
    public List<String> getErrors() {
        return errors;
    }
 
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

	public String getErrorCode() {
		if(errorCode == null) {
			//default error code : 10000
			errorCode = ErrorCode.DEFAULT_ERROR_CODE_10000;
		}
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
