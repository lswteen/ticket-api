package travel.config.exception;
/**
 *
 * @file OuterTicketValidException.java
 * @brief 외부티켓등록 예외
 * @author kyg
 * @date 생성 : 생성일(2017. 03. 24.)
 * @date 최종수정 : 최종 수정일(2017. 03. 24.)
 */
public class OuterTicketValidException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_DEFAULT = "외부티켓 등록 유효성 체크중 오류가 발생했습니다.";


	private String registConfirmFlag = "";

	public OuterTicketValidException(String message) {
		super(message);
	}

	public OuterTicketValidException(String message, String status) {
		super(message);
		this.registConfirmFlag = status;
	}

	public OuterTicketValidException() {
		super(MESSAGE_DEFAULT);
	}

	public String getRegistConfirmFlag() {
		return registConfirmFlag;
	}

	public void setRegistConfirmFlag(String registConfirmFlag) {
		this.registConfirmFlag = registConfirmFlag;
	}
}
