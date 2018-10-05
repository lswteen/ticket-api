package travel.api.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.type.Alias;

/**
 * Created by we on 2017. 3. 30..
 */
@Alias("ticket.auth")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthKey extends Entity{

    private Long apiKeyId;
    private String companyId, companyName, southBoundUrl, apiKeyType, apiKey, southBoundToken;
    private String allowIp, referer, scope;
    private AuthKeyRules authKeyRules;

    public AuthKey(){}
    public AuthKey(Long apiKeyId, String companyId,
                   String companyName, String apiKeyType, String apiKey,
                   String allowIp, String referer,String scope, String southBoundUrl, String southBoundToken
    ){
        super();
        this.apiKeyId = apiKeyId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.apiKeyType = apiKeyType;
        this.apiKey = apiKey;
        this.allowIp = allowIp;
        this.referer = referer;
        this.scope = scope;
        this.southBoundUrl = southBoundUrl;
        this.southBoundToken = southBoundToken;
    }

    public String getSouthBoundToken() {
        return southBoundToken;
    }

    public void setSouthBoundToken(String southBoundToken) {
        this.southBoundToken = southBoundToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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

    public AuthKeyRules getAuthKeyRules() {
        return authKeyRules;
    }

    public void setAuthKeyRules(AuthKeyRules authKeyRules) {
        this.authKeyRules = authKeyRules;
    }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

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

    public String getSouthBoundUrl() {
        return southBoundUrl;
    }

    public void setSouthBoundUrl(String southBoundUrl) {
        this.southBoundUrl = southBoundUrl;
    }
}
