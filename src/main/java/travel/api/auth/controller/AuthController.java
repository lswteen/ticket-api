package travel.api.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import travel.api.auth.domain.HttpAuthRequest;
import travel.api.auth.service.AuthService;
import travel.api.external.constants.CommonConstants;
import travel.api.external.web.binder.HttpRequestBinder;
import travel.api.external.web.binder.HttpResponseBinder;

import javax.inject.Inject;

/**
 * Created by we on 2017. 4. 4..
 */
@Controller
public class AuthController {
    
    @Inject
    private AuthService authService;

    /**
     * 인증키 조회
     * @param httpAuthRequest
     * @return
     */
    @RequestMapping(value="/agency/v1/auth/keys", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthKeys(@ModelAttribute HttpAuthRequest httpAuthRequest){
        httpAuthRequest.setApiCode(CommonConstants._APICODE.API_AUTH_001);
        HttpAuthRequest requestBinder = HttpRequestBinder.toAuth(httpAuthRequest);

        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK, authService.getAuthKeys(requestBinder));
        return responseEntity;
    }

    /**
     * 인증 키 생성
     * @param httpAuthRequest
     * @return
     */
    @RequestMapping(value="/agency/v1/auth/key", method = RequestMethod.POST)
    public ResponseEntity<?> generateRestApiKey(@RequestBody HttpAuthRequest httpAuthRequest){

        httpAuthRequest.setApiCode(CommonConstants._APICODE.API_AUTH_002);
        HttpAuthRequest requestBinder = HttpRequestBinder.toAuth(httpAuthRequest);

        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK, authService.generateRestApiKey(requestBinder));
        return responseEntity;
    }


    /**
     * 인증 키 수정
     * @param httpAuthRequest
     * @return
     */
    @RequestMapping(value="/agency/v1/auth/key", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyRestApiKey(@RequestBody HttpAuthRequest httpAuthRequest){
        httpAuthRequest.setApiCode(CommonConstants._APICODE.API_AUTH_003);
        HttpAuthRequest requestBinder = HttpRequestBinder.toAuth(httpAuthRequest);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK, authService.modifyRestApiKey(requestBinder));
        return responseEntity;
    }

    /**
     * 인증 키 갱신
     * @param httpAuthRequest
     * @return
     */
    @RequestMapping(value="/agency/v1/auth/key/renewal", method = RequestMethod.PUT)
    public ResponseEntity<?> restKeyRenewal(    @RequestBody HttpAuthRequest httpAuthRequest){
        httpAuthRequest.setApiCode(CommonConstants._APICODE.API_AUTH_004);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK,authService.restKeyRenewal(httpAuthRequest.getApiKeyId()));
        return responseEntity;
    }

    /**
     * 인증 키 삭제
     * @param httpAuthRequest
     * @return
     */
    @RequestMapping(value="/agency/v1/auth/key", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeRestApiKey(@RequestBody HttpAuthRequest httpAuthRequest){
        httpAuthRequest.setApiCode(CommonConstants._APICODE.API_AUTH_005);
        ResponseEntity<?> responseEntity = HttpResponseBinder.toResponseEntity(
                HttpStatus.OK, authService.removeRestApiKey(httpAuthRequest.getApiKeyId()));
        return responseEntity;
    }

}
