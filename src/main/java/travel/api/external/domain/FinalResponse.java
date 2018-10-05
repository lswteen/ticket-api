package travel.api.external.domain;

import org.apache.commons.lang3.StringUtils;
import travel.api.auth.dto.Entity;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by we on 2017. 5. 17..
 */
public class FinalResponse extends Entity{
    private String content;
    private String encodingType;
    private String mimeType;
    private int stautsCode;
    private String errorCode;
    private Map<String,String> responseHeaders;

    public FinalResponse(String content, String encodingType,
                         String mimeType, int statusCode, String errorCode, Map<String, String> responseHeaders) {
        this.content = content;
        this.encodingType = encodingType;
        this.mimeType = mimeType;
        this.stautsCode = stautsCode;
        this.errorCode = errorCode;
        this.responseHeaders = responseHeaders;
    }

    public int getContentBytesLength() throws UnsupportedEncodingException {
        if(StringUtils.isNotEmpty(content)){
            byte[] bytes = null;
            bytes = content.getBytes(encodingType);
            if(bytes!=null){
                return bytes.length;
            }
        }
        return 0;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEncodingType() {
        return encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public int getStautsCode() {
        return stautsCode;
    }

    public void setStautsCode(int stautsCode) {
        this.stautsCode = stautsCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }
}
