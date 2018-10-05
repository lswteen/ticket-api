package travel.health.dao;

import org.springframework.stereotype.Repository;

import travel.config.mybatis.ProductUserSqlSessionFactory;

import javax.inject.Inject;

/**
 * @brief database health check
 * @create 2015.06.01
 * @author nocode
 */
@Repository("HealthDao")
public class HealthDao {

    @Inject
    private ProductUserSqlSessionFactory productUserSqlSessionFactory;

    public int selectMysqlMasterDs() throws Exception {
        return productUserSqlSessionFactory.masterSelect().selectOne("api.sql.health.selectMasterDS");
    }

    public int selectMysqlSlaveDs() throws Exception {
        return productUserSqlSessionFactory.select().selectOne("api.sql.health.selectSlaveDS");
    }
}
