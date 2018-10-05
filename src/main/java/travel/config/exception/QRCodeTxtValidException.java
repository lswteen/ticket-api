package travel.config.exception;
/**
 *
 * @file QRCodeTxtValidException.java
 * @brief 외부티켓등록 예외
 * @author kyg
 * @date 생성 : 생성일(2017. 03. 24.)
 * @date 최종수정 : 최종 수정일(2017. 03. 24.)
 */
public class QRCodeTxtValidException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_DEFAULT = "외부티켓 등록 유효성 체크중 오류가 발생했습니다.";

	public QRCodeTxtValidException(String message) {
		super(message);
	}

	public QRCodeTxtValidException() {
		super(MESSAGE_DEFAULT);
	}
}
