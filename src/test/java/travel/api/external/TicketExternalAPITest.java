package travel.api.external;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import travel.api.external.utils.ValidationUtils;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * Created by we on 2017. 2. 27..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit-travel-context.xml","classpath:spring/junit-travel-servlet.xml"})
@WebAppConfiguration
public class TicketExternalAPITest {
    private static Logger logger = LoggerFactory.getLogger(TicketExternalAPITest.class);


    //@Test
    //@Ignore
    public void testAPI(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        String URI = "http://222.231.17.232:8080/api/registration.json";
        HttpMethod httpMethod = HttpMethod.POST;

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URI,httpMethod,httpEntity,String.class);
        logger.info("statusCode :{}, body : {}",responseEntity.getStatusCode(),responseEntity.getBody());
    }

    //@Test
    //public void mandatoryCheck(){
    //    ValidationUtils.mandatoryCheck("1","2","3");
    //}

    @Test
    public void randomNumber(){
        Random generator = new Random();
        String ticket = String.valueOf(generator.nextInt(10000));
        System.out.println(ticket);
    }


}
