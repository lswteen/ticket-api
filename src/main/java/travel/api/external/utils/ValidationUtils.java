package travel.api.external.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import travel.api.external.constants.ResponseCode;
import travel.api.external.domain.AbstractTransferObject;
import travel.api.external.dto.HttpTicketRequest;
import travel.api.external.dto.TicketItem;
import travel.api.external.exception.TicketApiException;
import travel.commons.utility.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by we on 2017. 3. 14..
 */
@Component
@PropertySource("classpath:properties/system.properties")
public class ValidationUtils {

    @Autowired
    private Environment env;

    /**
     * API auth TIme check
     * @param request
     */
    public void validationTimestamp(HttpServletRequest request){
        long limitTimeStamp = Long.parseLong(env.getProperty("timestamp.limit.count"));
        long requestTime = Long.parseLong(request.getHeader("timestamp"));
        long currentTime = System.currentTimeMillis();
        long time = (currentTime - requestTime);
        if(limitTimeStamp < time) throw new TicketApiException(ResponseCode.ValidationFail,
                new AbstractTransferObject());
    }

    /**
     * api request parametas mandatory check
     * @param args
     */
    public static void mandatoryCheck(String... args){
        for (String str : args) {
            //log.info("isResult : {}, str : {} ",StringUtil.isNullOrEmpty(str), str);
            if(true == StringUtil.isNullOrEmpty(str)){
                throw new TicketApiException(ResponseCode.BAD_REQUEST, new HttpTicketRequest());
            }
        }
    }

    public static void mandatoryCheck(Long arg){
        if(null == arg){
            throw new TicketApiException(ResponseCode.BAD_REQUEST, new HttpTicketRequest());
        }
    }


    /**
     * 숫자 타입 체크
     * @param type : parametas name
     * @param arg  : parametas value
     * @param transferObject : object
     * @return
     */
    public static String chkVaildNumber(String type, String arg, AbstractTransferObject transferObject){
        String numberRegEx = "^[0-9]*$";
        if(!Pattern.matches(numberRegEx,arg)){
            throw new TicketApiException(ResponseCode.BAD_REQUEST,
                    type.concat(" available only number format"), transferObject);
        }
        return arg;
    }

    /**
     * 리스트 사이즈 체크
     * @param
     */
    public static void chkSize(List<TicketItem> tickets){
        if(0 == tickets.size()){
            throw new TicketApiException(ResponseCode.BAD_REQUEST,
                    "There is no data." ,new AbstractTransferObject());
        }
    }


    /**
     * orderTime 시간 입력 체크
     * @param type
     * @param arg
     * @param transferObject
     * @return
     */
    public static String chkOrderTime(String type, String arg,  AbstractTransferObject transferObject){
        String regEx ="[0-9]{2}";
        if(!Pattern.matches(regEx,arg)){
            throw new TicketApiException(ResponseCode.BAD_REQUEST,
                    "Time information is not valid.",transferObject);
        }

        return arg;
    }

    /**
     * 길이 체크
     * @param type
     * @param arg
     * @param length
     * @param transferObject
     * @return
     */
    public static String chkVaildSize(String type, String arg, int length, AbstractTransferObject transferObject){
        if(arg.getBytes().length > length){
            throw new TicketApiException(ResponseCode.BAD_REQUEST,
                    type.concat(" maximum length is").concat(String.valueOf(length)).concat("byte"),transferObject);
        }
        return arg;
    }



    /**
     * 날짜 포맷 형식
     * ex) yyyy/MM/dd
     * @param dateToValidate
     * @param dateFromat
     * @return
     */
    public static String isThisDateValid(String type, String dateToValidate, String dateFromat,
                                  AbstractTransferObject transferObject){
        boolean isStatus = true;
        if(dateToValidate == null){
            isStatus = false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            //system.out.println(date);
            //log.debug("dateformat : {}",date);
        } catch (ParseException e) {
            //e.printStackTrace();
            isStatus=false;
        }

        if(isStatus == false){
            throw new TicketApiException(ResponseCode.BAD_REQUEST,
                    type.concat(" Dateformat is incorrect"), transferObject);
        }
        return dateToValidate;
    }

    public static void isPatternValid(String type, String pattern, String str){
        boolean flag = Pattern.matches(pattern,str);
        if(!flag){
            throw new TicketApiException(ResponseCode.BAD_REQUEST,
                    type.concat(" is no registration possible patterns."),new AbstractTransferObject());
        }

    }

}
