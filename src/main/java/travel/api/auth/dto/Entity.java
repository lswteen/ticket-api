package travel.api.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by we on 2017. 3. 30..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Entity implements Serializable, Cloneable{

    public Object clone(){
        Object obj = null;

        try{
            obj = super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return obj;
    }

    @JsonIgnore
    private String createId;
    @JsonIgnore
    private Timestamp createDate;
    @JsonIgnore
    private String modifyId;
    @JsonIgnore
    private Timestamp modifyDate;

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        //this.createId = createId;
        this.createId = "1000000001";
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }
}
