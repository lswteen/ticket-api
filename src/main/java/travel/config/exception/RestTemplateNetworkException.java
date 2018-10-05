package travel.config.exception;

public class RestTemplateNetworkException  extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6877817221027165101L;

	public RestTemplateNetworkException() {
        super();
    }

    public RestTemplateNetworkException(String msg) {
        super(msg);
    }
}
