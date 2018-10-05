package travel.api.external.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlRootElement;
import java.net.ProtocolFamily;

/**
 * Created by we on 2017. 2. 24..
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AbstractTransferObject {

    //private int index = 0;
    private String requestId;
    private ProtocolFamily serviceType;

    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * 해당 객체의 toString 오버라이드.
     *
     * @return 객체의 각 프로퍼티와 해당 값을 text로 제공
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /*\
     * 해당 객체의 equals 오버라이드.
     *
     * @param obj 비교 대상 객체
     * @return 비교 대상 객체와 각 프로퍼티값이 동일한 경우 <code>true</code>반환
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * 해당 객체의 hashCode 오버라이드.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
//    public int getIndex() {
//        return index;
//    }
//    public void setIndex(int index) {
//        this.index = index;
//    }

    public ProtocolFamily getServiceType() {
        return serviceType;
    }
    public void setServiceType(ProtocolFamily serviceType) {
        this.serviceType = serviceType;
    }
}
