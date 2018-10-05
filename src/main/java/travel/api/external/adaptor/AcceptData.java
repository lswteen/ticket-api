package travel.api.external.adaptor;

import org.springframework.http.HttpStatus;
import travel.api.external.domain.AbstractTransferObject;

import java.util.Map;
import java.util.UUID;

/**
 * Created by we on 2017. 5. 8..
 */
public class AcceptData extends AbstractTransferObject {

    private String URI;
    private String requestMethod;
    private String restKey;
    private String requestId, companyId, compnayName, southBoundToken;
    private String orgRemoteAddress;
    private String consumer;
    private Map<String,String> requestHeaders;
    private Map<String,String> requestParametas;
    private String payload;
    private Object requestPayload;
    private Object responsePayload;
    private Object sendPayload;
    private HttpStatus httpStatus;
    private long requestTimeMillis, authTimeMillis, assetLeadTime;

    public AcceptData(){
        requestId = UUID.randomUUID().toString();
        requestTimeMillis = System.currentTimeMillis();
    }

    public Object getResponsePayload() {
        return responsePayload;
    }

    public void setResponsePayload(Object responsePayload) {
        this.responsePayload = responsePayload;
    }

    public String getSouthBoundToken() {
        return southBoundToken;
    }

    public void setSouthBoundToken(String southBoundToken) {
        this.southBoundToken = southBoundToken;
    }

    public Object getSendPayload() {
        return sendPayload;
    }

    public void setSendPayload(Object sendPayload) {
        this.sendPayload = sendPayload;
    }

    public long getAssetLeadTime() {
        return assetLeadTime;
    }

    public void setAssetLeadTime(long assetLeadTime) {
        this.assetLeadTime = assetLeadTime;
    }

    public Object getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(Object requestPayload) {
        this.requestPayload = requestPayload;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public long getRequestTimeMillis() {
        return requestTimeMillis;
    }

    public void setRequestTimeMillis(long requestTimeMillis) {
        this.requestTimeMillis = requestTimeMillis;
    }

    public long getAuthTimeMillis() {
        return authTimeMillis;
    }

    public void setAuthTimeMillis(long authTimeMillis) {
        this.authTimeMillis = authTimeMillis;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRestKey() {
        return restKey;
    }

    public void setRestKey(String restKey) {
        this.restKey = restKey;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOrgRemoteAddress() {
        return orgRemoteAddress;
    }

    public void setOrgRemoteAddress(String orgRemoteAddress) {
        this.orgRemoteAddress = orgRemoteAddress;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Map<String, String> getRequestParametas() {
        return requestParametas;
    }

    public void setRequestParametas(Map<String, String> requestParametas) {
        this.requestParametas = requestParametas;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompnayName() {
        return compnayName;
    }

    public void setCompnayName(String compnayName) {
        this.compnayName = compnayName;
    }
}
