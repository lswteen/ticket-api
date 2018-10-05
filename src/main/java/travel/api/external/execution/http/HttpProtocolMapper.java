package travel.api.external.execution.http;


import com.beust.jcommander.internal.Maps;
import org.springframework.http.HttpStatus;
import travel.api.external.constants.ResponseCode;

import java.util.Map;

/**
 * Created by we on 2017. 2. 24..
 */
public class HttpProtocolMapper {
    private static final Map<ResponseCode, HttpStatus> httpStatusMap = Maps.newHashMap();
    static{
        httpStatusMap.put(ResponseCode.SUCCESS, HttpStatus.OK);
        httpStatusMap.put(ResponseCode.CREATE, HttpStatus.CREATED);
        httpStatusMap.put(ResponseCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.DOSE_NOT_REQUIRED_HEADER, HttpStatus.PRECONDITION_FAILED);
        httpStatusMap.put(ResponseCode.INVALID_REST_KEY, HttpStatus.UNAUTHORIZED);
        httpStatusMap.put(ResponseCode.INVALID_AGENCY_CODE,HttpStatus.UNAUTHORIZED);

        httpStatusMap.put(ResponseCode.NotFound, HttpStatus.NOT_FOUND);
        httpStatusMap.put(ResponseCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        httpStatusMap.put(ResponseCode.SERVICE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        httpStatusMap.put(ResponseCode.ValidationFail, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.ACCESS_DENIED_REST_KEY, HttpStatus.UNAUTHORIZED);
        httpStatusMap.put(ResponseCode.ACCESS_DENIED_IP, HttpStatus.UNAUTHORIZED);
/*
        httpStatusMap.put(ResponseCode.DeleteNotSupported, HttpStatus.FORBIDDEN);
        httpStatusMap.put(ResponseCode.Duplicate, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.ForeignKeyViolation, HttpStatus.BAD_REQUEST);

        httpStatusMap.put(ResponseCode.ADDRESS_ERROR, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.MULTIMEDIA_CONTENT_REFUSED, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.MESSAGE_ID_NOT_FOUND, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.MESSAGE_FORMAT_CORRUPT, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.CONTENT_ERROR, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.CLIENT_ERROR, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.CLIENT_UNAVAILABLE, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.CONTENT_SIZE_ERROR, HttpStatus.REQUEST_ENTITY_TOO_LARGE);
        httpStatusMap.put(ResponseCode.SUBJECT_SIZE_ERROR, HttpStatus.BAD_REQUEST);

        httpStatusMap.put(ResponseCode.SERVICE_UNAVAILABLE, HttpStatus.GATEWAY_TIMEOUT);
        httpStatusMap.put(ResponseCode.SERVICE_DENIED, HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
        httpStatusMap.put(ResponseCode.SERVICE_CHECK, HttpStatus.SERVICE_UNAVAILABLE);

        httpStatusMap.put(ResponseCode.EXCEED_MAX_TRANS, HttpStatus.INTERNAL_SERVER_ERROR);
        httpStatusMap.put(ResponseCode.MAIL_ADDRESS_ERROR, HttpStatus.BAD_REQUEST);
        httpStatusMap.put(ResponseCode.EXCEED_MAX_FILE_SIZE, HttpStatus.REQUEST_ENTITY_TOO_LARGE);
        httpStatusMap.put(ResponseCode.EXCEED_MAX_FILE_COUNT, HttpStatus.REQUEST_ENTITY_TOO_LARGE);
        */
    }

    public static HttpStatus getHttpStatus(ResponseCode response){
        return httpStatusMap.get(response);
    }

}
