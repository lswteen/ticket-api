package travel.api.external.constants;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by we on 2017. 2. 27..
 */
@Component
@PropertySource({"classpath:properties/system.properties"})
public class CommonConstants {
    /** The env. */
    public static final String errorMapField = "field";
    public static final String errorMapMessage = "message";

    public enum ServiceType{
        TOUR
    }

    public enum _APICODE{
        API_EIS_001("API_EIS_001"), API_EIS_002("API_EIS_002"),
        API_EIS_003("API_EIS_003"), API_EIS_004("API_EIS_004"),
        API_EIS_005("API_EIS_005"), API_EIS_006("API_EIS_006"),
        API_EIS_008("API_EIS_008"), API_EIS_010("API_EIS_010"),
        API_EIS_011("API_EIS_011"), API_EIS_012("API_EIS_012"),
        API_IIS_001("API_IIS_001"), API_IIS_002("API_IIS_002"),
        API_IIS_003("API_IIS_003"), API_IIS_004("API_IIS_004"),
        API_AUTH_001("API_AUTH_001"), API_AUTH_002("API_AUTH_002"),
        API_AUTH_003("API_AUTH_003"), API_AUTH_004("API_AUTH_004"),
        API_AUTH_005("API_AUTH_005"), API_AUTH_006("API_AUTH_006");
        private String code;
        private _APICODE(String code){
            this.code = code;
        };
        public String getCode() {return code;}
    }
}
