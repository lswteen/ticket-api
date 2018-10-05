package travel.util;

public class EncryptException extends RuntimeException {
    private static final long serialVersionUID = -8080295336833778257L;

    public EncryptException() {
        super("암호화 오류가 발생했습니다.");
    }
}
