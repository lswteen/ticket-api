package travel.api.auth.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import travel.api.auth.dao.AuthDao;
import travel.api.auth.domain.HttpAuthRequest;
import travel.api.auth.dto.AuthKey;
import travel.api.auth.dto.AuthKeyRules;
import travel.api.auth.service.AuthService;
import travel.api.external.constants.ResponseCode;
import travel.api.external.domain.AbstractTransferObject;
import travel.api.external.exception.TicketApiException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * Created by we on 2017. 3. 30..
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthDao authDao;

    private String generateKey(){
        return UUID.randomUUID().toString();
    }

    @Override
    public AuthKey generateRestApiKey(HttpAuthRequest httpAuthRequest) {
        //uuid 생성
        String apiKey = generateKey();
        //생성된 restkey 를 추가한다. database | redis 사용고려 database 에 등록
        AuthKey authkey = new AuthKey();
        authkey.setCompanyId(httpAuthRequest.getCompanyId());
        authkey.setCompanyName(httpAuthRequest.getCompanyName());
        authkey.setApiKeyType(httpAuthRequest.getApiKeyType());
        authkey.setSouthBoundUrl(httpAuthRequest.getSouthBoundUrl());
        authkey.setSouthBoundToken(httpAuthRequest.getSouthBoundToken());
        authkey.setApiKey(apiKey);
        Long isResult = authDao.insertAuth(authkey);
        authkey.setApiKeyId(isResult);
        return authkey;
    }

    @Override
    public boolean restKeyRenewal(Long apikeyId) {
        AuthKey authkey = new AuthKey();
        authkey.setApiKey(generateKey());
        authkey.setApiKeyId(apikeyId);
        Integer isResult = authDao.updateAuth(authkey);
        //redis 데이터 변경
        if(isResult == 0){
            throw new TicketApiException(ResponseCode.NotFound, new AbstractTransferObject());
        }
        return true;
    }

    private boolean isApiKeyReule(Long apiKeyId,String type){
        String isResult = "";
        if("IP".equals(type)){
            isResult = authDao.isAllowIpCheck(apiKeyId);
        }else if("REFERER".equals(type)){
            isResult = authDao.isRefererCheck(apiKeyId);
        }else if("SCOPE".equals(type)){
            isResult = authDao.isScopeCheck(apiKeyId);
        }
        return StringUtils.isNotEmpty(isResult);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public boolean modifyRestApiKey(HttpAuthRequest httpAuthRequest){
        AuthKey authKey= new AuthKey(httpAuthRequest.getApiKeyId(), httpAuthRequest.getCompanyId(),
                httpAuthRequest.getCompanyName(), httpAuthRequest.getApiKeyType(),
                httpAuthRequest.getApiKey(), httpAuthRequest.getAllowIp(), httpAuthRequest.getReferer(),
                httpAuthRequest.getScope(),httpAuthRequest.getSouthBoundUrl(),httpAuthRequest.getSouthBoundToken());

        Integer isResult = authDao.updateAuth(authKey);

        if(isResult == 0){
            throw new TicketApiException(ResponseCode.NotFound, new AbstractTransferObject());
        }

        if(StringUtils.isNotEmpty(httpAuthRequest.getReferer())){
            if(isApiKeyReule(httpAuthRequest.getApiKeyId(),"REFERER")){
                authDao.updateRefererRule(authKey);
            }else{
                authKey.setModifyId("1000000001");
                authKey.setCreateId("1000000001");
                authDao.insertRefererRule(authKey);
            }
        }

        if(StringUtils.isNotEmpty(httpAuthRequest.getAllowIp())){
            if(isApiKeyReule(httpAuthRequest.getApiKeyId(),"IP")){
                authDao.updateIpRule(authKey);
            }else{
                authKey.setModifyId("1000000001");
                authKey.setCreateId("1000000001");
                authDao.insertIpRule(authKey);
            }
        }

        if(StringUtils.isNotEmpty(httpAuthRequest.getScope())){
            if(isApiKeyReule(httpAuthRequest.getApiKeyId(),"SCOPE")){
                authDao.updateScopeRule(authKey);
            }else{
                authKey.setModifyId("1000000001");
                authKey.setCreateId("1000000001");
                authDao.insertScopeRule(authKey);
            }
        }
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public boolean removeRestApiKey(Long apiKeyId) {
        authDao.deleteIpRule(apiKeyId);
        authDao.deleteRefererRule(apiKeyId);
        authDao.deleteScopeRule(apiKeyId);
        Integer isResult = authDao.deleteAuth(apiKeyId);
        if(isResult == 0){
            throw new TicketApiException(ResponseCode.NotFound, new AbstractTransferObject());
        }
        return true;
    }

    private List<String> strToken(String str){
        StringTokenizer st = new StringTokenizer(str,"|");
        List<String> result = new ArrayList<String>();
        while(st.hasMoreElements()){
            result.add(st.nextToken());
        }
        return result;
    }

    @Override
    public List<AuthKey> getAuthKeys(HttpAuthRequest httpAuthRequest)
    {
        AuthKey authKey = new AuthKey();
        authKey.setApiKeyId(httpAuthRequest.getApiKeyId());
        authKey.setCompanyId(httpAuthRequest.getCompanyId());
        authKey.setApiKey(httpAuthRequest.getApiKey());

        List<AuthKey> isResult = authDao.selectAuth(authKey);
        List<AuthKey> customResult = new ArrayList<AuthKey>();

        for (AuthKey item: isResult) {
            AuthKey auth = new AuthKey();
            auth = item;
            AuthKeyRules authKeyRules = new AuthKeyRules();
            if(null!=item.getReferer()){
                authKeyRules.setAuthReferrerRules(strToken(item.getReferer()));
                auth.setReferer(null);
            }
            if(null!=item.getAllowIp()){
                authKeyRules.setAuthIpRules(strToken(item.getAllowIp()));
                auth.setAllowIp(null);
            }
            if(null!=item.getScope()){
                authKeyRules.setAuthScopeRules(strToken(item.getScope()));
                auth.setScope(null);
            }
            auth.setAuthKeyRules(authKeyRules);
            customResult.add(auth);
        }
        return customResult;
    }
}
