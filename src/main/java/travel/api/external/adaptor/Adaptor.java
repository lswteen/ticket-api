package travel.api.external.adaptor;

import org.springframework.http.ResponseEntity;

/**
 * Created by we on 2017. 5. 8..
 */
public interface Adaptor {
    public ResponseEntity<String> execute(AcceptData acceptdata);
    public void executeSlack(AcceptData acceptdata);
}
