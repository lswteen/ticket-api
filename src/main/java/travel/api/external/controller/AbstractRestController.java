package travel.api.external.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by we on 2017. 2. 24..
 */

@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
public abstract class AbstractRestController {


}
