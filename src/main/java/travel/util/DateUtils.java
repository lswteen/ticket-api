package travel.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @brief 날짜관련 유틸
 * @date 생성 : 2016. 12. 23.
 * @date 최종수정 : 2016. 12. 23.
 */
public class DateUtils {
	
	/**
	 * 오늘 날짜와 target 날짜를 비교하여 차이값 리턴
	 * @param target 비교할 날짜
	 * @return long
	 */
	public static long compareToday(String target) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		long diffDays = 0;
		
		// 오늘날짜
		String today = getToday();
	     
	    try {
	    	Date beginDate = formatter.parse(today);
	    	Date endDate = formatter.parse(target);
	         
	    	// 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
	    	long diff = endDate.getTime() - beginDate.getTime();
	    	diffDays = diff / (24 * 60 * 60 * 1000);
	         
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
	    return diffDays;
		
	}
	
	/**
	 * 제한날짜에서 비교날짜를 뺀 차이
	 * @param limitDate 제한날짜
	 * @param compareDate 비교날짜
	 * @return long
	 */
	public static long compareDay(String limitDate, String compareDate) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		long diffDays = 0;
		  
	    try {
	    	Date beginDate = formatter.parse(compareDate);
	    	Date endDate = formatter.parse(limitDate);
	         
	    	// 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
	    	long diff = endDate.getTime() - beginDate.getTime();
	    	diffDays = diff / (24 * 60 * 60 * 1000);
	         
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
	    return diffDays;
		
	}
	
	/**
	 * 오늘날짜를 문자열로
	 * @return String
	 */
	public static String getToday() {
		
		// 오늘날짜
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1 ;
		int day = cal.get(Calendar.DATE) ;
		
		// 달이 1~9이면 0을 붙여줌
		String strMonth = (month < 10) ? "0" + String.valueOf(month) : String.valueOf(month);
		
		// 일이 1~9이면 0을 붙여줌
		String strDay = (day < 10) ? "0" + String.valueOf(day) : String.valueOf(day);
				
		return String.valueOf(year) + strMonth + String.valueOf(strDay);
		
	}
	
	/**
	 * 날짜계산
	 * @param basicDate 기준일
	 * @param amount 경과일
	 * @return
	 */
	public static String addDate(String basicDate, Integer amount) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		
		try {
			cal.setTime(formatter.parse(basicDate));
			cal.add(Calendar.DAY_OF_MONTH, amount);
		} catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		return formatter.format(cal.getTime());
		
	}




}
