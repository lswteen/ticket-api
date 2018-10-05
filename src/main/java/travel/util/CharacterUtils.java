package travel.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import travel.commons.utility.AES256CipherStatic;


public class CharacterUtils {

	private static Log LOGGER = LogFactory.getLog(CharacterUtils.class);

    /**
     * 한글의 바이트 수를 계산 : SMS 전송시에 이용함
     */
    public static int getByteLength(String text) throws UnsupportedEncodingException {
        int length = text.getBytes("EUC-KR").length;
        return length;
    }

    /**
     * 첫단위 숫자에 콤바를 찍어줌
     */
    public static String convertThousandFormat(Object text) {
        DecimalFormat df = new DecimalFormat("#,###,###,##0");
        String result = df.format(text);
        return result;
    }

    /**
     * 표준 날짜 포멧으로 변환
     * 20140108235959 -> 2014-01-08 23:59:59
     */
    public static String convertRegulaDateFormat(String value) {
        String result = null;

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date inputDate = inputFormat.parse(value);

            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = outputFormat.format(inputDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

	public static String encrypt(String str) {
		if (str == null) {
			return null;
		}

		if (str.trim().equals("")) {
			return "";
		}

		try {

			return AES256CipherStatic.encrypt(str);
		} catch (Exception e) {
			LOGGER.error("order encrypt error : <" + str + ">, " + e.toString());
			return str;
		}

	}

	public static String decrypt(String str) {
		if (str == null) {
			return null;
		}

		if (str.trim().equals("")) {
			return "";
		}

		try {

			return AES256CipherStatic.decrypt(str);
		} catch (Exception e) {
			LOGGER.error("order decrypt error : <" + str + ">, " + e.toString());
			return str;
		}
	}
}