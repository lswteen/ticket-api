package travel.api.auth.dao;

import org.springframework.stereotype.Repository;
import travel.api.auth.dto.AuthKey;
import travel.config.mybatis.ProductUserSqlSessionFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by we on 2017. 4. 3..
 */
@Repository
public class AuthDao {

    @Inject
    private ProductUserSqlSessionFactory sqlSessionFactory;

    public List<AuthKey> selectAuth(AuthKey authKey){
        return sqlSessionFactory.select().selectList("sql.auth.selectAuth",authKey);
    }

    public Long insertAuth(AuthKey authKey){
        Integer isResult = sqlSessionFactory.cud().insert("sql.auth.insertAuth",authKey);
        return authKey.getApiKeyId();
    }

    public Integer updateAuth(AuthKey authKey){
        return sqlSessionFactory.cud().update("sql.auth.updateAuth", authKey);
    }

    public Integer deleteAuth(Long apiKeyId){
        return sqlSessionFactory.cud().delete("sql.auth.deleteAuth",apiKeyId);
    }

    public Integer insertIpRule(AuthKey authKey){
        return sqlSessionFactory.cud().insert("sql.auth.insertIpRule",authKey);
    }

    public String isAllowIpCheck(Long apiKeyId){
        return sqlSessionFactory.select().selectOne("sql.auth.isAllowIpCheck",apiKeyId);
    }

    public String isRefererCheck(Long apiKeyId){
        return sqlSessionFactory.select().selectOne("sql.auth.isRefererCheck",apiKeyId);
    }

    public String isScopeCheck(Long apiKeyId){
        return sqlSessionFactory.select().selectOne("sql.auth.isScopeCheck",apiKeyId);
    }

    public Integer updateIpRule(AuthKey authKey){
        return sqlSessionFactory.cud().update("sql.auth.updateIpRule", authKey);
    }

    public Integer deleteIpRule(Long apiKeyId){
        return sqlSessionFactory.cud().delete("sql.auth.deleteIpRule",apiKeyId);
    }

    public Integer insertRefererRule(AuthKey authKey){
        return sqlSessionFactory.cud().insert("sql.auth.insertRefererRule",authKey);
    }

    public Integer updateRefererRule(AuthKey authKey){
        return sqlSessionFactory.cud().update("sql.auth.updateRefererRule", authKey);
    }

    public Integer deleteRefererRule(Long apiKeyId){
        return sqlSessionFactory.cud().delete("sql.auth.deleteRefererRule",apiKeyId);
    }

    public Integer insertScopeRule(AuthKey authKey){
        return sqlSessionFactory.cud().insert("sql.auth.insertScopeRule",authKey);
    }

    public Integer updateScopeRule(AuthKey authKey){
        return sqlSessionFactory.cud().insert("sql.auth.updateScopeRule",authKey);
    }

    public Integer deleteScopeRule(Long apiKeyId){
        return sqlSessionFactory.cud().insert("sql.auth.deleteScopeRule",apiKeyId);
    }

    public AuthKey selectSouthBoundUrl(String companyId){
        return sqlSessionFactory.select().selectOne("sql.auth.selectSouthBoundUrl",companyId);
    }
}
