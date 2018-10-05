package travel.api.external.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlTransient;
import java.sql.Timestamp;

/**
 * Created by we on 2017. 2. 24..
 */
@XmlTransient
@JsonAutoDetect
public abstract class AbstractObject {
    @JsonIgnore
    private Timestamp createDateTime;

    @JsonIgnore
    private Timestamp modifyDateTime;

    // Ticket API 사용시 request ID
    private String requestId;

    @JsonIgnore
    private String parameter;

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Timestamp getModifyDateTime() {
        return modifyDateTime;
    }

    public void setModifyDateTime(Timestamp modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
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

    /**
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

}
