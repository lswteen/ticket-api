package travel.api.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by we on 2017. 3. 30..
 * 인증 필터
 * IP, Referer
 */
@Alias("ticket.keyrules")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthKeyRules extends Entity{

    private List<String> authIpRules;
    private List<String> authReferrerRules;
    private List<String> authScopeRules;

    public List<String> getAuthScopeRules() {
        return authScopeRules;
    }

    public void setAuthScopeRules(List<String> authScopeRules) {
        this.authScopeRules = authScopeRules;
    }

    public AuthKeyRules(){}

    public List<String> getAuthIpRules() {
        return authIpRules;
    }

    public void setAuthIpRules(List<String> authIpRules) {
        this.authIpRules = authIpRules;
    }

    public List<String> getAuthReferrerRules() {
        return authReferrerRules;
    }

    public void setAuthReferrerRules(List<String> authReferrerRules) {
        this.authReferrerRules = authReferrerRules;
    }
}
