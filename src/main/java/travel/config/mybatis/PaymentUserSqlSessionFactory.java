/**
 * @file    UserSqlSessionFactory.java
 * @brief   Master/Slave DB연결을 위한 Factory class 
 * @author  개발지원 1팀
 * @author  성우현
 * @date    생성: 2014-03-14
 * @date    최종수정: 2014-04-25
 */
package travel.config.mybatis;

/**
 * @breif Master/Slave/SMS DB연결을 위한 Factory Class
 * @author 개발지원1팀/제비파트/성우현
 * @version 1.0.0
 * @date 생성: 2014-03-14
 * @date 수정: 2014-04-25
 * @remark
 */
public class PaymentUserSqlSessionFactory {
	private PaymentUserSqlSessionMaster sqlMaster;
	private PaymentUserSqlSessionSlave sqlSlave;
	private PaymentUserSqlSessionMasterSelect sqlMasterSelect;

	public PaymentUserSqlSessionFactory() {
	}
	
	/**
	 * 
	 * @param sqlMaster Master 처리용 메서드를 포함한 UserSqlSessionMaster 객체
	 * @param sqlSlave Master 처리용 메서드를 포함한 UserSqlSessionSlave 객체
	 * @param sqlMasterSelect Master select 처리용 메서드를 포함한 UserSqlSessionMasterSelect 객체
	 */
	public PaymentUserSqlSessionFactory(PaymentUserSqlSessionMaster sqlMaster,
			PaymentUserSqlSessionSlave sqlSlave,
			PaymentUserSqlSessionMasterSelect sqlMasterSelect
			) {
		this.sqlMaster = sqlMaster;
		this.sqlSlave = sqlSlave;
		this.sqlMasterSelect = sqlMasterSelect;
	}

	/**
	 * @breif 조회 DB
	 * @return UserSqlSessionSlave 객체
	 */
	public PaymentUserSqlSessionSlave select() {
		return sqlSlave;
	}

	/**
	 * @breif 입력/수정/삭제 DB
	 * @return UserSqlSessionMaster 객체
	 */
	public PaymentUserSqlSessionMaster cud() {
		return sqlMaster;
	}

	/**
	 * @breif Master DB 조회
	 * @return UserSqlSessionMasterSelect 객체
	 */
	public PaymentUserSqlSessionMasterSelect masterSelect() {
		return sqlMasterSelect;
	}
}
