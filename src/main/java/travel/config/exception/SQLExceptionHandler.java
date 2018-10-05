package travel.config.exception;

import java.sql.SQLException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * travel.config.exception
 *    |_ SQLExceptionHandler.java
 * @brief
 * @author 어드민/박종성
 * @version 1.0
 * @date 생성 : 2014. 12. 15.
 * @date 최종수정 : 2014. 12. 15.
 */
@ControllerAdvice
public class SQLExceptionHandler {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 * @brief
	 * @Method Name: handleSQLException
	 * @author: 어드민/박종성
	 * @date: 2014. 12. 15.
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Object> handleSQLException(SQLException ex) {	
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ErrorCode.SQL_ERROR_CODE_10003);
		LOGGER.error(ExceptionUtils.getStackTrace(ex));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		return new ResponseEntity<Object>(errorMessage, headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
