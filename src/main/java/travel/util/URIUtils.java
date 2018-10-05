package travel.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by we on 2017. 8. 25..
 */
@Component
@PropertySource("classpath:properties/${env.type}.travel.properties")
public class URIUtils {

    @Autowired
    public Environment env;

    /**
     * queryString
     * @param params
     * @return
     */
    public String paramsToQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            sb.append(param.getKey()).append("=").append(param.getValue()).append("&");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length()-1);
        }
        return sb.toString();
    }

    public String createURI(Map<String,String> params, String pathParam){
        StringBuilder URI = new StringBuilder();
        URI.append(env.getProperty("pay.domain"));
        URI.append(pathParam);
        if(!params.isEmpty()){
            URI.append("?".concat(paramsToQueryString(params)));
        }
        return URI.toString();
    }

    public String createURI(String pathParam){
        StringBuilder URI = new StringBuilder();
        URI.append(env.getProperty("pay.domain"));
        URI.append(pathParam);
        return URI.toString();
    }
}
