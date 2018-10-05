package travel.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * @brief 변환관련 유틸
 * @date 생성 : 2017. 1. 9.
 * @date 최종수정 : 2017. 1. 9.
 */
public class ConvertUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConvertUtil.class);
	
	public static String stringConversion(Object object) {
		
		String paramsStr = "";
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			paramsStr = mapper.writeValueAsString(object);
		} catch(Exception e) {
			throw new IllegalArgumentException("[ConvertUtils - stringConversion] : String convert to Object error");
		}
    	
		return paramsStr;
		
    }
	
	public static Object dtoConversion(Type convertType, Object object) {
		
		try {
			Gson gson = new Gson();
			String jsonData = stringConversion(object);
			Object result = gson.fromJson(jsonData, convertType);
	    	
			LOGGER.debug("=====> Convert jsonData : "+jsonData);
			LOGGER.debug("=====> Convert Object type : "+convertType);
	    	
			return result;
			
		} catch(Exception e) {
			throw new IllegalArgumentException("[ConvertUtils - dtoConversion] : json convert to object error");
		}
		
	}
	
	/**
	 * 객체(dto)를 map으로 변환
	 * @param object 객체
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> mapConversion(Object object) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			// dto field get
			for (Field field : object.getClass().getDeclaredFields()){
				field.setAccessible(true);
				result.put(field.getName(), field.get(object));
			}
			
		} catch(Exception e) {
			// TODO
		}
		
		return result;
		
	}
	
	/**
	 * 두개의 dto 합치기; sub의 값을 main에 옮겨서 리턴; 같은 field 이름을 가지고 있는 맴버끼리 값 이동
	 * @param main 리턴이 되는 dto
	 * @param sub 값을 가지고 있는 dto
	 * @param updateFlag main에 값이 존재하면  sub의 값으로 업데이트할지의 여부
	 * @return Object
	 */
	public static Object mergeObject(Object main, Object sub, boolean updateFlag) {
		
		try {
			// main dto field get
			for (Field mainField : main.getClass().getDeclaredFields()){
				// sub dto field get
				for (Field subField : sub.getClass().getDeclaredFields()){
					// main field name과 sub field name 같으면
					if(mainField.getName().equals(subField.getName())) {
						
						mainField.setAccessible(true);
						
						// 업데이플래그가 'Y'이거나 업데이트플레그가 'N'이지만 값이 비어 있으면... 값복사
						if(updateFlag || (!updateFlag && ValidatorUtil.isEmpty(mainField.get(main)))) {
							subField.setAccessible(true);
							mainField.set(main, subField.get(sub)); // 값 복사
							break;
						}
					}
				}
			}
		} catch(Exception e) {
			// TODO
		}
		
		return main;
				
	}
	
	/**
	 * dto와 map 합치기; 필드명과 key명이 같으면 값 복제
	 * @param main 리턴되는 dto
	 * @param sub 값을 가지고 있는 map
	 * @param updateFlag main에 값이 존재하면  sub의 값으로 업데이트할지의 여부
	 * @return
	 */
	public static Object mergeObjectMap(Object main, Map<String, Object> sub, boolean updateFlag) {
		
		try {
			// main dto field get
			for (Field mainField : main.getClass().getDeclaredFields()){
				// sub dto field get
				for(String key : sub.keySet()){
					// main field name과 sub key 같으면
					if(mainField.getName().equals(key)) {
						
						mainField.setAccessible(true);
						
						// 업데이플래그가 true이거나 업데이트플레그가 false이지만 main 값이 비어 있거나 sub 값이 존재할 경우 값복사
						if(updateFlag || (!updateFlag && ValidatorUtil.isEmpty(mainField.get(main))) 
								|| !ValidatorUtil.isEmpty(sub.get(key))) {
							// 변수 타입별 형변환 후 값 복사
							if(Integer.class == mainField.getType()) {
								mainField.set(main, Integer.valueOf(String.valueOf(sub.get(key)))); // 값 복사
							} else if(Double.class == mainField.getType()) {
								mainField.set(main, Double.valueOf(String.valueOf(sub.get(key)))); // 값 복사
							} else if(Long.class == mainField.getType()) {
								mainField.set(main, Long.valueOf(String.valueOf(sub.get(key)))); // 값 복사
							} else if(String.class == mainField.getType()) {
								mainField.set(main, String.valueOf(sub.get(key))); // 값 복사
							}
							
							break;
						}
					}
		        }
			}
		} catch(Exception e) {
			// TODO
			e.printStackTrace();
		}
		
		return main;
				
	}

}
