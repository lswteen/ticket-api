package travel.api.external.constants;

/**
 * Created by we on 2017. 2. 24..
 */
public enum ResponseCode {
    SUCCESS(1000, "Ok"),                                                                    //성공
    CREATE(1000, "Created"),                                                                //등록 완료
    BAD_REQUEST(4000, "Bad Request"),                                                       //파라메타 정보가 정확하지 않음
    DOSE_NOT_REQUIRED_HEADER(4001, " It does not exist the value of the Required Header"),  //RestKey 정보가 정확하지 않음
    INVALID_REST_KEY(4002, "Invalid RestKey"),                                              //RestKey 정보가 정확하지 않음
    INVALID_AGENCY_CODE(4003, "Invalid Agency Code"),                                       //등록된 agency 정확하지 없음
    NotFound(9005, "Resource not found"),                                                   //리소스를 찾을수 없음
    ACCESS_DENIED_REST_KEY(9006, "Access Denied"),
    ACCESS_DENIED_IP(9007, "Access Denied"),
    INTERNAL_SERVER_ERROR(5000, "Internal server error"),                                   //내부 서버오류
    SERVICE_ERROR(4005, "General service error"),                                           //일반적인 서비스오류
    READ_TIME_OUT(9700, "Asset Read Time out"),
    CONNECT_TO_ASSET_TIME_OUT(9710, "Connect to Asset Time out"),
    CONNECTION_TO_ASSET_REFUSED(9710, "Connection to Asset refused"),
    CONNECTION_COLSED(9720,"Connection Closed"),
    ValidationFail(9004, "Validation Faild");

    //구매완료 상품입니다 Purchase completion is a commodity.
    //환불완료 상품입니다. Refund completion is a commodity.
    //요청한 상품이 없습니다 You do not have the requested items.
/*
    ADDRESS_ERROR(2002, "Missing number or unknown phone numbers"),
    MAIL_ADDRESS_ERROR(2003, "Invalid Email address format"),
    MULTIMEDIA_CONTENT_REFUSED(2004, "Unsupported media type or mime format"),
    MESSAGE_ID_NOT_FOUND(2005, "Message id not found"),
    MESSAGE_FORMAT_CORRUPT(2007, "Invalid message format or contents"),
    CONTENT_ERROR(2101, "Invalid contents"),
    CLIENT_ERROR(2102, "A temporary problem of terminal"),
    CLIENT_UNAVAILABLE(2103, "Unsupported terminal"),
    CONTENT_SIZE_ERROR(2104, "Can not execute large size contents."),
    SUBJECT_SIZE_ERROR(2105, "Can not execute large size subject."),
    SERVICE_UNAVAILABLE(4006, "Service timeout"),
    SERVICE_DENIED(4007, "Permission denied"),
    EXCEED_MAX_TRANS(4008, "Exceed Max Trans"),
    SERVICE_CHECK(4101, "The server is under inspection"),
    EXCEED_MAX_FILE_SIZE(4131,"Exceed Max Transport files size"),
    EXCEED_MAX_FILE_COUNT(4132,"Exceed Max file count"),
    Duplicate(9001, "ID Duplicated"),
    DeleteNotSupported(9002, "Delete default code not supported"),
    ForeignKeyViolation(9003, "foreignkey  violation"),

    */


    private final Integer code;
    private final String message;

    private ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public static ResponseCode getInstance(int code){
        ResponseCode instance = null;
        for(ResponseCode response : ResponseCode.values()){
            if(response.getCode() == code)
                instance = response;
        }

        if(instance == null){
            return ResponseCode.INTERNAL_SERVER_ERROR;
        }

        return instance;
    }

}
