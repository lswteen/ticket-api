/**
 * @file    UserSqlSessionMasterSelect.java
 * @brief   Master DB사용시 필요한 Select메서드 구현 class 
 * @author  개발1팀
 * @author  성우현
 * @date    생성: 2014-03-14
 * @date    최종수정: 2014-03-14
 */
package travel.config.mybatis;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @breif   Master DB사용시 필요한 Select메서드 구현 클래스 
 * @author  개발1팀/제비파트/성우현
 * @version 1.0.0
 * @date    생성: 2014-03-14
 * @date    수정: 2014-03-14
 * @remark
 */
public class ProductUserSqlSessionMasterSelect {
    private SqlSession sqlSession; 

    /**
     * @breif 생성자
     * @param sqlSession Master연결에 필요한 SqlSession 객체
     */
    public ProductUserSqlSessionMasterSelect(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
    
    public <T> T selectOne(String statement) throws Exception {
        return sqlSession.selectOne(statement);
    }

    public <T> T selectOne(String statement, Object parameter) throws Exception {
        return sqlSession.selectOne(statement, parameter);
    }

    public <E> List<E> selectList(String statement) throws Exception {
        return sqlSession.selectList(statement);
    }

    public <E> List<E> selectList(String statement, Object parameter) throws Exception {
        return sqlSession.selectList(statement, parameter);
    }
}
