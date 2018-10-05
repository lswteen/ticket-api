package travel.security;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

public class XSSUtils {

    private XSSUtils()
    {

    }

    public static String stripXSS(String value) {
        return value == null ? value : escapeHtml4(value);
    }
}