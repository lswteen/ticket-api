package travel.api.auth.service;


import travel.api.auth.domain.HttpAuthRequest;
import travel.api.auth.dto.AuthKey;
import travel.api.auth.dto.AuthKeyRules;

import java.util.List;

/**
 * Created by we on 2017. 3. 30..
 */
public interface AuthService {

    /**
     * rest key 생성
     * @HttpAuthRequest
     * companyId 파트너사ID
     * companyName 파트너사명
     * apiKeyType keyType : server, javascript, java, php, Android, iOS
     * @return
     */
    public AuthKey generateRestApiKey(HttpAuthRequest httpAuthRequest);

    /**
     * rest Key 갱신
     * @param apikeyId
     * @return
     */
    public boolean restKeyRenewal(Long apikeyId);

    /**
     * rest key 수정
     * @return
     */
    public boolean modifyRestApiKey(HttpAuthRequest httpAuthRequest);

    /**
     * ret Key 삭제
     * @return
     */
    public boolean removeRestApiKey(Long apiKeyId);

    /**
     * rest key 전체조회
     * apiKeyId, companyId, apiKey (3개정 택일)
     * @return
     */
    public List<AuthKey> getAuthKeys(HttpAuthRequest httpAuthRequest);

}
