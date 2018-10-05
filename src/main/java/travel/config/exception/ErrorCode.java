package travel.config.exception;

/**
 * 
 * travel.config.exception
 *    |_ ErrorCode.java
 * @brief
 * @author 어드민/박종성
 * @version 1.0
 * @date 생성 : 2014. 12. 17.
 * @date 최종수정 : 2014. 12. 17.
 */
public class ErrorCode {
	
	//에러코드 10000은 eror코드가 정의되지 않은 예외는 10000으로 반환한다.
	public static final String DEFAULT_ERROR_CODE_10000 = "10000";
	
	//Illegal 에러
	public static final String CONFLICT_ERROR_CODE_10001 = "10001";
	
	//Nullpointer 에러
	public static final String NULLPOINT_ERROR_CODE_10002 = "10002";
	
	//SQL 에러 발생
	public static final String SQL_ERROR_CODE_10003 = "10003";
	
	/*배송 API (주소정제) */
	/*
	 * 
	//필수항목 오류 에러발생 
	public static final String REQUIREINPUT_ERROR_CODE_20000 = "20000";
	//등록되지 않은 고객 ID 
	public static final String UKNOWNCUSTOMER_ERROR_CODE_20001 = "20001";
	//주소분석 실패 
	public static final String ADDRESS_ERROR_CODE_20002 = "20002";
	//집배권역 추출 실패 
	public static final String POSTDIV_ERROR_CODE_20003 = "20003";
	//점소정보 추출실패 
	public static final String DLVBRAND_ERROR_CODE_20004 = "20004";
	//사원정보 추출실패 
	public static final String EMPID_ERROR_CODE_20005 = "20005";
	//도착지코드 추출실패
	public static final String DLVENDCODE_ERROR_CODE_20006 = "20006";
	//분류주소 추출실패
	public static final String DIVADDRESS_ERROR_CODE_20007= "20007";
	//계약운임 추출실패
	public static final String FAREDIV_ERROR_CODE_20008="20008";
	*/
	
	
}
