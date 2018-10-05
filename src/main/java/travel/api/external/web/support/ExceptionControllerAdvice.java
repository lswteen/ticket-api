package travel.api.external.web.support;

import com.beust.jcommander.internal.Maps;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import travel.api.external.constants.CommonConstants;
import travel.api.external.constants.ResponseCode;
import travel.api.external.domain.ErrorResponse;
import travel.api.external.exception.TicketApiException;
import travel.api.external.exception.TicketException;
import travel.api.external.execution.ErrorDetails;
import travel.api.external.execution.http.HttpProtocolMapper;
import travel.api.external.logging.TicketLoggingSupport;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by we on 2017. 2. 24..
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
    // Exception Mapping HashMap
    private Map<Class<?>, HttpStatus> exceptionMapping = Maps.newHashMap();
    @Autowired
    private TicketLoggingSupport ticketLoggingSupport;

    @PostConstruct
    public void initialize() {
        this.exceptionMapping.put(NoSuchRequestHandlingMethodException.class, HttpStatus.NOT_FOUND);
        this.exceptionMapping.put(HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED);
        this.exceptionMapping.put(HttpMediaTypeNotSupportedException.class, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        this.exceptionMapping.put(HttpMediaTypeNotAcceptableException.class, HttpStatus.NOT_ACCEPTABLE);
        this.exceptionMapping.put(MissingServletRequestParameterException.class, HttpStatus.BAD_REQUEST);
        this.exceptionMapping.put(ServletRequestBindingException.class, HttpStatus.BAD_REQUEST);
        this.exceptionMapping.put(ConversionNotSupportedException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        this.exceptionMapping.put(HttpMessageNotReadableException.class, HttpStatus.BAD_REQUEST);
        this.exceptionMapping.put(HttpMessageNotWritableException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        this.exceptionMapping.put(MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST);
        this.exceptionMapping.put(MissingServletRequestPartException.class, HttpStatus.BAD_REQUEST);
        this.exceptionMapping.put(BindException.class, HttpStatus.BAD_REQUEST);
        this.exceptionMapping.put(TypeMismatchException.class, HttpStatus.BAD_REQUEST);
        this.exceptionMapping.put(SocketTimeoutException.class, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        HttpStatus errorStatus = exceptionMapping.get(ex.getClass()) != null ? exceptionMapping.get(ex.getClass()) : HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseCode responseCode = errorStatus == HttpStatus.INTERNAL_SERVER_ERROR ? ResponseCode.INTERNAL_SERVER_ERROR : ResponseCode.BAD_REQUEST;

        ErrorResponse response = null;
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setId(String.valueOf(errorStatus.value()));
        errorDetails.setCode(String.valueOf(responseCode.getCode()));
        errorDetails.setMessage(errorStatus.getReasonPhrase());
        errorDetails.setService(CommonConstants.ServiceType.TOUR.name());
        response = new ErrorResponse(errorDetails);

        ResponseEntity<?> responseEntity = new ResponseEntity<ErrorResponse>(response, errorStatus);
        ticketLoggingSupport.error(responseEntity, ex.getMessage());
        return responseEntity;
    }


    @ExceptionHandler(value = TicketException.class)
    public ResponseEntity<?> handleException(TicketException ex) {
        ex.printStackTrace();
        HttpStatus errorStatus = HttpProtocolMapper.getHttpStatus(ex.getResponseCode());

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setId(String.valueOf(errorStatus.value()));
        errorDetails.setCode(String.valueOf(ex.getCode()));
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setService(CommonConstants.ServiceType.TOUR.name());
        ErrorResponse response = new ErrorResponse(errorDetails);

        ResponseEntity<?> responseEntity = new ResponseEntity<ErrorResponse>(response, errorStatus);
        ticketLoggingSupport.error(responseEntity, ex.getMessage());
        return responseEntity;
    }

    @ExceptionHandler(value = TicketApiException.class)
    public ResponseEntity<?> handleException(HttpServletRequest request, TicketApiException ex) {
        ex.printStackTrace();
        HttpStatus errorStatus = HttpProtocolMapper.getHttpStatus(ex.getResponseCode());
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setId(String.valueOf(errorStatus.value()));
        errorDetails.setCode(String.valueOf(ex.getCode()));
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setService("WeMakePrice");
        ErrorResponse response = new ErrorResponse(errorDetails);

        ResponseEntity<?> responseEntity = new ResponseEntity<ErrorResponse>(response, errorStatus);
        ticketLoggingSupport.error(responseEntity, ex.getMessage());
        return responseEntity;
    }

    @ExceptionHandler(value = SocketTimeoutException.class)
    public ResponseEntity<?> handleException(SocketTimeoutException ex){
        ex.printStackTrace();
        HttpStatus errorStatus = exceptionMapping.get(ex.getClass()) != null ? exceptionMapping.get(ex.getClass()) : HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseCode responseCode = errorStatus == HttpStatus.INTERNAL_SERVER_ERROR ? ResponseCode.INTERNAL_SERVER_ERROR : ResponseCode.READ_TIME_OUT;
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setId(String.valueOf(errorStatus.value()));
        errorDetails.setCode(String.valueOf(responseCode.getCode()));
        errorDetails.setMessage(responseCode.getMessage());
        errorDetails.setService("Asset-Service");
        ErrorResponse response = new ErrorResponse(errorDetails);
        ResponseEntity<?> responseEntity = new ResponseEntity<ErrorResponse>(response, errorStatus);
        ticketLoggingSupport.error(responseEntity, ex.getMessage());
        return responseEntity;
    }

    @ExceptionHandler(value = ConnectTimeoutException.class)
    public ResponseEntity<?> handleException(ConnectTimeoutException ex){
        ex.printStackTrace();
        HttpStatus errorStatus = exceptionMapping.get(ex.getClass()) != null ? exceptionMapping.get(ex.getClass()) : HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseCode responseCode = errorStatus == HttpStatus.INTERNAL_SERVER_ERROR ? ResponseCode.INTERNAL_SERVER_ERROR : ResponseCode.CONNECT_TO_ASSET_TIME_OUT;

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setId(String.valueOf(errorStatus.value()));
        errorDetails.setCode(String.valueOf(responseCode.getCode()));
        errorDetails.setMessage(responseCode.getMessage());
        errorDetails.setService("Asset-Service");
        ErrorResponse response = new ErrorResponse(errorDetails);
        ResponseEntity<?> responseEntity = new ResponseEntity<ErrorResponse>(response, errorStatus);
        ticketLoggingSupport.error(responseEntity, ex.getMessage());
        return responseEntity;
    }

    @ExceptionHandler(value = JsonMappingException.class)
    public ResponseEntity<?> handleException(JsonMappingException ex){
        ex.printStackTrace();
        HttpStatus errorStatus = exceptionMapping.get(ex.getClass()) != null ? exceptionMapping.get(ex.getClass()) : HttpStatus.BAD_REQUEST;
        ResponseCode responseCode = errorStatus == HttpStatus.BAD_REQUEST ? ResponseCode.BAD_REQUEST : ResponseCode.BAD_REQUEST;

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setId(String.valueOf(errorStatus.value()));
        errorDetails.setCode(String.valueOf(responseCode.getCode()));
        errorDetails.setMessage(responseCode.getMessage());
        errorDetails.setService("WeMakePrice");
        ErrorResponse response = new ErrorResponse(errorDetails);
        ResponseEntity<?> responseEntity = new ResponseEntity<ErrorResponse>(response, errorStatus);
        ticketLoggingSupport.error(responseEntity, ex.getMessage());
        return responseEntity;
    }

    @ExceptionHandler(value = HttpHostConnectException.class)
    public ResponseEntity<?> handleException(HttpHostConnectException ex){
        ex.printStackTrace();
        HttpStatus errorStatus = exceptionMapping.get(ex.getClass()) != null ? exceptionMapping.get(ex.getClass()) : HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseCode responseCode = errorStatus == HttpStatus.INTERNAL_SERVER_ERROR ? ResponseCode.INTERNAL_SERVER_ERROR : ResponseCode.CONNECTION_TO_ASSET_REFUSED;

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setId(String.valueOf(errorStatus.value()));
        errorDetails.setCode(String.valueOf(responseCode.getCode()));
        errorDetails.setMessage(responseCode.getMessage());
        errorDetails.setService("Asset-Service");
        ErrorResponse response = new ErrorResponse(errorDetails);
        ResponseEntity<?> responseEntity = new ResponseEntity<ErrorResponse>(response, errorStatus);
        ticketLoggingSupport.error(responseEntity, ex.getMessage());
        return responseEntity;
    }

    @ExceptionHandler(value = ConnectionClosedException.class)
    public ResponseEntity<?> handleException(ConnectionClosedException ex){
        ex.printStackTrace();
        HttpStatus errorStatus = exceptionMapping.get(ex.getClass()) != null ? exceptionMapping.get(ex.getClass()) : HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseCode responseCode = errorStatus == HttpStatus.INTERNAL_SERVER_ERROR ? ResponseCode.INTERNAL_SERVER_ERROR : ResponseCode.CONNECTION_COLSED;

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setId(String.valueOf(errorStatus.value()));
        errorDetails.setCode(String.valueOf(responseCode.getCode()));
        errorDetails.setMessage(responseCode.getMessage());
        errorDetails.setService("Asset-Service");
        ErrorResponse response = new ErrorResponse(errorDetails);
        ResponseEntity<?> responseEntity = new ResponseEntity<ErrorResponse>(response, errorStatus);
        ticketLoggingSupport.error(responseEntity, ex.getMessage());
        return responseEntity;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        HttpStatus errorStatus = exceptionMapping.get(ex.getClass()) != null ? exceptionMapping.get(ex.getClass()) : HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setId(String.valueOf(errorStatus.value()));
        errorDetails.setCode(String.valueOf(ResponseCode.ValidationFail.getCode()));
        errorDetails.setMessage(ResponseCode.ValidationFail.getMessage());
        errorDetails.setService(CommonConstants.ServiceType.TOUR.name());

        List<Map<String, String>> errors = new ArrayList<Map<String, String>>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, String> errorMap = null;

        for (FieldError fieldError : fieldErrors) {
            errorMap = Maps.newHashMap();
            errorMap.put(CommonConstants.errorMapField, fieldError.getField());
            errorMap.put(CommonConstants.errorMapMessage, fieldError.getDefaultMessage());
            errors.add(errorMap);
        }
        errorDetails.setDetails(errorMap);

        ErrorResponse response = new ErrorResponse(errorDetails);

        ResponseEntity<?> responseEntity = new ResponseEntity<ErrorResponse>(response, errorStatus);
        ticketLoggingSupport.error(responseEntity, ex.getMessage());
        return responseEntity;
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleException(MissingServletRequestParameterException ex){
        ex.printStackTrace();

        HttpStatus errorStatus = null;
        ResponseCode responseCode = null;
        if(ex.getMessage().contains("companyId")){
            responseCode = ResponseCode.INVALID_REST_KEY;
            errorStatus = HttpProtocolMapper.getHttpStatus(responseCode);
        }else{
            responseCode = ResponseCode.BAD_REQUEST;
            errorStatus = exceptionMapping.get(ex.getClass()) != null ? exceptionMapping.get(ex.getClass()) : HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setId(String.valueOf(errorStatus.value()));
        errorDetails.setCode(String.valueOf(responseCode.getCode()));
        errorDetails.setMessage(responseCode.getMessage());
        errorDetails.setService("-");

        ErrorResponse response = new ErrorResponse(errorDetails);
        ResponseEntity<?> responseEntity = new ResponseEntity<ErrorResponse>(response, errorStatus);
        ticketLoggingSupport.error(responseEntity, ex.getMessage());
        return responseEntity;

    }




}
