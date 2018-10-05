package travel.api.Internel.dao;

import org.springframework.stereotype.Repository;
import travel.config.mybatis.ProductUserSqlSessionFactory;

import javax.inject.Inject;

/**
 * Created by we on 2017. 10. 26..
 */
@Repository
public class InternelDao {

    @Inject
    private ProductUserSqlSessionFactory sqlSessionFactory;

    public String getCompanyOption(String optionId){
        return sqlSessionFactory.select().selectOne("sql.ticket.getCompanyOption",optionId);
    }

    public String getWeMakePriceOption(String companyOptionId){
        return sqlSessionFactory.select().selectOne("sql.ticket.getWeMakePriceOption",companyOptionId);
    }
}
