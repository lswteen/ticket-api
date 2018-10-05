package travel.config.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * travel.config.exception
 *    |_ GlobalDefaultExceptionHandler.java
 * @brief
 * @author 박종성
 * @version 1.0
 * @date 생성 : 2014. 12. 11.
 * @date 최종수정 : 2016. 11. 30. response 형식 변경에 의한 수정
 */
@ControllerAdvice
class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        ErrorMessage errorMessage = new ErrorMessage(errors);
        LOGGER.error(ExceptionUtils.getStackTrace(ex));
        return new ResponseEntity<Object>(errorMessage, headers, status);
    }
 
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String exMessage = ex.getLocalizedMessage();
        ErrorMessage errorMessage = new ErrorMessage(exMessage);
        LOGGER.error(ExceptionUtils.getStackTrace(ex));
        return new ResponseEntity<Object>(errorMessage, headers, status);
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String unsupported = "Unsupported content type: " + ex.getContentType();
        String supported = "Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes());
        ErrorMessage errorMessage = new ErrorMessage(unsupported, supported);
        LOGGER.error(ExceptionUtils.getStackTrace(ex));
        return new ResponseEntity<Object>(errorMessage, headers, status);
    }
 
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        ErrorMessage errorMessage;
        if (mostSpecificCause != null) {
            String exceptionName = mostSpecificCause.getClass().getName();
            String message = mostSpecificCause.getMessage();
            errorMessage = new ErrorMessage(exceptionName, message);
        } else {
            errorMessage = new ErrorMessage(ex.getMessage());
        }
        LOGGER.error(ExceptionUtils.getStackTrace(ex));
        return new ResponseEntity<Object>(errorMessage, headers, status);
    }
    
    /**
     * 
     * @brief
     * @Method Name: handleConflict
     * @author: 어드민/박종성
     * @date: 2014. 12. 17.
     * @param ex
     * @return
     */
//    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
//    protected ResponseEntity<Object> handleConflict(RuntimeException ex) {
////        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ErrorCode.CONFLICT_ERROR_CODE_10001);
////        LOGGER.error(ExceptionUtils.getStackTrace(ex));
////        HttpHeaders headers = new HttpHeaders();
////        headers.add("Content-Type", "application/json; charset=UTF-8");
////        return new ResponseEntity<Object>(errorMessage, headers, HttpStatus.CONFLICT);
//    
//    }
//    
//    /**
//     * 
//     * @brief
//     * @Method Name: handleNoData
//     * @author: 어드민/박종성
//     * @date: 2014. 12. 17.
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(value = { NullPointerException.class})
//    protected ResponseEntity<Object> handleNoData(RuntimeException ex) {
////        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ErrorCode.NULLPOINT_ERROR_CODE_10002);
////        LOGGER.error(ExceptionUtils.getStackTrace(ex));
////        HttpHeaders headers = new HttpHeaders();
////        headers.add("Content-Type", "application/json; charset=UTF-8");
////        return new ResponseEntity<Object>(errorMessage, headers, HttpStatus.BAD_REQUEST);
//    	
//   
//    }
}
