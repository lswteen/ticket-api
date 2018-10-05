package travel.api.auth;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by we on 2017. 4. 4..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit-travel-context.xml","classpath:spring/junit-travel-servlet.xml"})
public class AuthTest {
    private static Logger logger = LoggerFactory.getLogger(AuthTest.class);


    @Test
    @Ignore
    public void generateKey(){
        System.out.println(UUID.randomUUID().toString());
    }

    private List<String> strToken(String str){
        StringTokenizer st = new StringTokenizer(str,"|");
        List<String> result = new ArrayList<String>();
        while(st.hasMoreElements()){
            result.add(st.nextToken());
        }
        return result;
    }

    @Test
    public void tokenList(){
        String str = "2000|3000|4000";
        List<String> result = strToken(str);
        result.forEach(item->System.out.println(item));
    }
}
