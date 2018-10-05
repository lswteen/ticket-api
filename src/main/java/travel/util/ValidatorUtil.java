package travel.util;

import java.util.List;

/**
 * @brief 유효성 체크 관련 유틸
 * @date 생성 : 2016. 11. 30.
 * @date 최종수정 : 2016. 11. 30.
 */
public class ValidatorUtil {
	
	/**
	 * not null 체크
	 * @param object 객체
	 * @param message 오류메세지
	 */
	public static void isNotNull(Object object, String message) {
		
        if (object == null)
            throw new IllegalArgumentException(message + " 필수항목 입니다");
        
    }
	
	/**
	 * blank 체크
	 * @param object 객체
	 * @param message 오류메세지
	 */
	public static void isNotEmpty(Object object, String message){
		
    	if (object instanceof Integer || object instanceof Long) {
            Integer value = object.toString().length();
            if (value == 0)
            	throw new IllegalArgumentException(message + " 필수항목 입니다");
        }
        if (object instanceof String) {
            String value = (String) object;
            if (value.length() == 0)
            	throw new IllegalArgumentException(message + " 필수항목 입니다");
        }
        
    }
	
	/**
	 * 값이 비어있는지 체크
	 * @param object 객체
	 * @return boolean
	 */
	public static boolean isEmpty(Object object){
		
    	if (object instanceof Integer || object instanceof Long) {
            Integer value = object.toString().length();
            if (value == 0)
            	return true;
        }
        if (object instanceof String) {
            String value = (String) object;
            if (value.length() == 0)
            	return true;
        }
        
        return false;
        
    }
    
    /**
     * 필수 체크
     * @param item 체크항목
     * @param message 오류메세지
     */
    public static void isRequired(Object item, String message){
    	
    		isNotNull(item,message);
    		isNotEmpty(item,message);
    		
    }
	
    /**
     * 지정된 값 체크
     * @param item 체크할 값
     * @param validChar 지정된 값
     * @param message 오류메세지
     */
    public static void isValidForm(String item, String[] validChar, String message) {
    	
        boolean valid = false;
        
        for (String value : validChar) {
            if (item.equals(value)) {
                valid = true;
                break;
            }
        }
        
        if (!valid)
            throw new IllegalArgumentException(message + " 알수 없는 형식입니다");
        
    }
    
    /**
     * 리스트 데이타 없음
     * @param listCount 검색 리스트 카운터
     * @param message 오류메세지
     */
    public static void noData(Object object, String message) {
    	
    	if (object instanceof List<?>) {
    		if(((List<?>) object).size() < 1)
            	throw new IllegalArgumentException(message + " 정보가 없습니다");
    	} else {
    		if(object == null)
    			throw new IllegalArgumentException(message + " 정보가 없습니다");
    	}
    	
    }

}
