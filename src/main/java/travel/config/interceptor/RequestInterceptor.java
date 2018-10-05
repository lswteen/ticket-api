package travel.config.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import travel.api.auth.domain.HttpAuthRequest;
import travel.api.auth.dto.AuthKey;
import travel.api.auth.service.AuthService;
import travel.api.auth.support.IpReferrerValidator;
import travel.api.external.adaptor.AcceptData;
import travel.api.external.constants.ResponseCode;
import travel.api.external.dto.HttpTicketRequest;
import travel.api.external.exception.TicketApiException;
import travel.api.external.logging.TicketLoggingSupport;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Aspect
public class RequestInterceptor{

	@Autowired
	private IpReferrerValidator ipReferrerValidator;

	@Autowired
	private AuthService authService;

	@Autowired
	private TicketLoggingSupport ticketLoggingSupport;

	@Pointcut("execution(* travel..*TicketRestController*.*(..))")
	public void authExecution() {}

	@Before("authExecution()")
	public void Before(JoinPoint joinPoint) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		//RestKey 체크
		List<AuthKey> isResults = validRestKey(request);
		//IP 체크
		validWhiteListIP(request, isResults);
	}

	private List<AuthKey> validRestKey(HttpServletRequest request) {
		String restKey = request.getHeader("restKey");
		HttpAuthRequest httpAuthRequest = new HttpAuthRequest();
		httpAuthRequest.setApiKey(restKey);
		List<AuthKey> isResults = authService.getAuthKeys(httpAuthRequest);

		if(0 == isResults.size()){
			throw new TicketApiException(ResponseCode.ACCESS_DENIED_REST_KEY, new HttpTicketRequest());
		}
		if(!isResults.get(0).getApiKey().equals(restKey)){
			throw new TicketApiException(ResponseCode.ACCESS_DENIED_REST_KEY, new HttpTicketRequest());
		}
		request.setAttribute("companyId",isResults.get(0).getCompanyId());
		
		return isResults;
	}

	private void validWhiteListIP(HttpServletRequest request, List<AuthKey> isResults) {
		if(null != isResults.get(0).getAuthKeyRules()){
			//IP 화이트리스트 정책
			boolean isResult = ipReferrerValidator.validateForAuthIp(request.getRemoteAddr(),
					isResults.get(0).getAuthKeyRules().getAuthIpRules());
			if(isResult == false){
				throw new TicketApiException(ResponseCode.ACCESS_DENIED_IP, new HttpTicketRequest());
			}
		}
	}

	@Pointcut("execution(* travel..*RestController*.*(..))")
	public void externalAPILogger() {}

	@AfterReturning("externalAPILogger()")
	public void afterExternalAPILogger(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		AcceptData acceptData =(AcceptData)request.getAttribute("data");
		if(null == request.getAttribute("companyId")){
			acceptData.setCompanyId("tnc-payment");
		}else{
			acceptData.setCompanyId(String.valueOf(request.getAttribute("companyId")));
		}
		ticketLoggingSupport.info(acceptData);
	}

}
