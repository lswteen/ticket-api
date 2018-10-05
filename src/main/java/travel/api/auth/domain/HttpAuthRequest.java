package travel.api.auth.domain;

import travel.api.auth.dto.AuthKeyRules;
import travel.api.external.constants.CommonConstants;
import travel.api.external.domain.AbstractTransferObject;

/**
 * Created by we on 2017. 4. 4..
 */
public class HttpAuthRequest extends AbstractTransferObject{
    private CommonConstants._APICODE apiCode;        //API 구분
    private Long apiKeyId;
    private String companyId, companyName, southBoundUrl, apiKeyType, apiKey, southBoundToken;
    private String allowIp, referer, scope;
    private AuthKeyRules authKeyRules;

    public String getSouthBoundToken() {
        return southBoundToken;
    }

    public void setSouthBoundToken(String southBoundToken) {
        this.southBoundToken = southBoundToken;
    }

    public CommonConstants._APICODE getApiCode() {
        return apiCode;
    }

    public void setApiCode(CommonConstants._APICODE apiCode) {
        this.apiCode = apiCode;
    }

    public Long getApiKeyId() {
        return apiKeyId;
    }

    public void setApiKeyId(Long apiKeyId) {
        this.apiKeyId = apiKeyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getApiKeyType() {
        return apiKeyType;
    }

    public void setApiKeyType(String apiKeyType) {
        this.apiKeyType = apiKeyType;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getAllowIp() {
        return allowIp;
    }

    public void setAllowIp(String allowIp) {
        this.allowIp = allowIp;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public AuthKeyRules getAuthKeyRules() {
        return authKeyRules;
    }

    public void setAuthKeyRules(AuthKeyRules authKeyRules) {
        this.authKeyRules = authKeyRules;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSouthBoundUrl() {
        return southBoundUrl;
    }

    public void setSouthBoundUrl(String southBoundUrl) {
        this.southBoundUrl = southBoundUrl;
    }
}
