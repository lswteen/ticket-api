package travel.util;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OuterValidatorUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(OuterValidatorUtils.class);


	/**
     * 리스트 데이타 없음
     * @param listCount 검색 리스트 카운터
     * @param message 오류메세지
     */
    public static void noData(Integer listCount, String message) {

    	if(listCount < 1) {
    		throw new IllegalArgumentException(message + " 정보가 없습니다");
    	}

    }

    /**
     * 조회데이터 정보
     * @param item
     * @param message
     */
    public static void noData(Object object, String message) {
    	if (object == null)
            throw new IllegalArgumentException(message + " 정보가 없습니다");
    }

    /**
     * 값이 비어있는지 여부
     * @param item
     */
    public static boolean isEmpty(Object item){

    	if(item == null) {
    		return true;
    	} else {
	    	if (item instanceof Integer || item instanceof Long || item instanceof Double) {
	    		if ("0".equals(item.toString()) || "0.0".equals(item.toString())) return true;
	        } else if (item instanceof String) {
	        	if ("".equals((String) item)) return true;
	        }
    	}

    	return false;

    }



	// old
    public static void isNotNull(Object object, String message) {
        if (object == null)
            throw new IllegalArgumentException(message + " 필수항목 입니다");
    }

    public static void isNotEmpty(Object item, String message){
    	if (item instanceof Integer || item instanceof Long) {
            Integer value = item.toString().length();
            if (value == 0)
            		throw new IllegalArgumentException(message + " 필수항목 입니다");
        }
        if (item instanceof String) {
            String value = (String) item;
            if (value.length() == 0)
            		throw new IllegalArgumentException(message + " 필수항목 입니다");
        }
    }

    public static void isRequired(Object item, String message){
    		isNotNull(item,message);
    		isNotEmpty(item,message);
    }

    // 크거나 작거나 하면 안되고 지정된 사이즈만 허용
    // 코드성 데이터에 적합
    public static void isFixLength(Object item, Integer length, String message) {
        if (item instanceof Integer || item instanceof Long) {
            Integer value = item.toString().length();
            if (value != length)
                throw new IllegalArgumentException(message + " " + length + " 자리 입니다");
        }
        if (item instanceof String) {
            String value = (String) item;
            if (value.length() != length)
                throw new IllegalArgumentException(message + " " + length + " 자리 입니다");
        }
    }

    // 최소 자리수 체크
    public static void isMinLength(Object item, Integer length, String message) {
        if (item instanceof Integer || item instanceof Long) {
            Integer value = item.toString().length();
            if (value < length)
                throw new IllegalArgumentException(message + " 최소 " + length + " 자리 입니다");
        }
        if (item instanceof String) {
            String value = (String) item;
            if (value.length() < length)
                throw new IllegalArgumentException(message + " 최소 " + length + " 자리 입니다");
        }
    }

    // 최대 자리수 체크
    public static void isMaxLength(Object item, Integer length, String message) {
        if (item instanceof Integer || item instanceof Long) {
            Integer value = item.toString().length();
            if (value > length)
                throw new IllegalArgumentException(message + " 최대 " + length + " 자리 입니다");
        }
        if (item instanceof String) {
            String value = (String) item;
            if (value.length() > length)
                throw new IllegalArgumentException(message + " 최대 " + length + " 자리 입니다");
        }
    }

    // 최대 자리수 체크
    public static void isLength(Object item, Integer minLength, Integer maxLength, String message) {
    		isMinLength(item,minLength,message);
    		isMaxLength(item,maxLength,message);
    }

    // 숫자인지 체크
    public static void isNumberic(String item, String message) {
        if (item == null)
            return;

        if (NumberUtils.isNumber(item) == false) {
            throw new IllegalArgumentException(message + " 숫자만 입력하세요");
        }
    }

    // 이메일 형식 체크
    public static void isEmailValid(String item, String message) {
        if (Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", item.trim()) == false)
            throw new IllegalArgumentException(message + " 잘못된 이메일 형식입니다");

        // 이것도 맞는 건지?
//      if (Pattern.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", object.trim()) == false)
//          throw new IllegalArgumentException(message + " 잘못된 이메일 형식입니다");
    }

    // 지정된 형식만 체크
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

    // 날짜 형식 체크 (yyyyMMdd)
    public static void isValidDate(String item, String message) {
        boolean dateValidity = true;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
        sdf.setLenient(false);

//        try{
//            Date dt =  sdf.parse(item);
//
//        }catch(ParseException pe){
//            dateValidity = false;
//        }catch(IllegalArgumentException ae){
//            dateValidity = false;
//        }

        if (dateValidity == false)
            throw new IllegalArgumentException(message + " 잘못된 날짜 형식입니다");
    }

    public static void isBusinessNumber(String item, String message){
    		Integer[] checkIds = {1, 3, 7, 1, 3, 7, 1, 3, 5, 1};

    		Integer chkSum = 0, remander=null;
    		String c2="";

    		for (int i=0; i<=7; i++) chkSum += (checkIds[i] * Character.getNumericValue(item.charAt(i)));

    		c2 = "0" + String.valueOf(checkIds[8] * Character.getNumericValue(item.charAt(8)));
    		c2 = c2.substring(c2.length()-2,c2.length());
    		chkSum += ( Character.getNumericValue(c2.charAt(0)) + Character.getNumericValue(c2.charAt(1)) );

    		remander = (10 - (chkSum % 10)) % 10;

    		if( remander == null || !remander.equals(Character.getNumericValue(item.charAt(9))) )
    			throw new IllegalArgumentException(message + " 올바른 형식이 아닙니다.");
    }

}
