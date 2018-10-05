package travel.api.external.exception.handler;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by we on 2017. 2. 24..
 */
public class PassResponseErrorHandler implements ResponseErrorHandler {
    /* (non-Javadoc)
	 * @see org.springframework.web.client.ResponseErrorHandler#hasError(org.springframework.http.client.ClientHttpResponse)
	 */
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return false;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.client.ResponseErrorHandler#handleError(org.springframework.http.client.ClientHttpResponse)
     */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // nothing
    }
}
