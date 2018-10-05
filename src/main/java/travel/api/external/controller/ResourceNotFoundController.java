package travel.api.external.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import travel.api.external.web.binder.HttpResponseBinder;

/**
 * Created by we on 2017. 6. 26..
 */
@Controller
public class ResourceNotFoundController {
    @RequestMapping("/error404")
    public ResponseEntity<?> error404(){
        return HttpResponseBinder.toResponseEntity(HttpStatus.NOT_FOUND);
    }
}
