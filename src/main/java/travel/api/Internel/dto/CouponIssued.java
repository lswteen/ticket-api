package travel.api.Internel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by we on 2017. 4. 19..
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponIssued {

    private String productId;               //상품 일련번호
    private String ticketType;              //외부 내부
    private String ticketCodeType;          //바코드, QR, TXT
    private String companyId;               //파트너 일련번호
    private List<OptionIssued> options;     //옵션정보
    private String pinNo;                   //대행사 바코드번호
    private String optionId;                //위메프 옵션 ID

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketCodeType() {
        return ticketCodeType;
    }

    public void setTicketCodeType(String ticketCodeType) {
        this.ticketCodeType = ticketCodeType;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public List<OptionIssued> getOptions() {
        return options;
    }

    public void setOptions(List<OptionIssued> options) {
        this.options = options;
    }

    public String getPinNo() {
        return pinNo;
    }

    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }
}
