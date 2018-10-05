package travel.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import travel.api.external.adaptor.AcceptData;
import travel.api.external.adaptor.Adaptor;

/**
 * Created by we on 2017. 9. 5..
 */
@Component
public class LogUtils {

//    @Autowired
//    private ElasticSearchConfig elasticSearchConfig;

    @Autowired
    private Adaptor adaptor;

    @Value("${slack.domain.ticket}")
    private String slackBotDomain;


    public void createDocument(AcceptData acceptData, long currentTime
            , long totalmethodTime, long assetLeadTime, long tourProcessTime) {

//        String logIndex = ".commonlog-" + new SimpleDateFormat("yyyy.MM.dd").format(new Date());
//
//        TransportClient client = elasticSearchConfig.connectionFactory();
//
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                .getRequest();
//        try {
//            client.prepareIndex(logIndex, "api-log").setSource(jsonBuilder()
//                .startObject()
//                .field("serviceId","ticket")
//                .startObject("external")
//                .field("restKey", "")
//                .field("companyId", "")
//                .endObject()
//                .field("currentTime", currentTime)
//                .field("currentUrl", request.getRequestURI())
//                .field("httpStatus", acceptData.getHttpStatus())
//                .field("methodTime", totalmethodTime)
//                .field("assetTime", assetLeadTime)
//                .field("extendTime", tourProcessTime)
//                .field("consumerId", acceptData.getConsumer())
//                .field("responseData", acceptData.getPayload())
//                .field("requestData", "")
//                .endObject()).execute(new ActionListener<IndexResponse>() {
//                public void onFailure(Exception e) {e.printStackTrace();}
//                public void onResponse(IndexResponse response) {}
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void sendSlackMessage(String requestPayload){
//        JsonObject obj = new JsonObject();
//        obj.addProperty("token","xoxb-263885488724-r8b1U3T2eluu5VzPUtTIKhvY");
//        obj.addProperty("channel","%23general");
//        obj.addProperty("text","123123");
//        String requestPayload = obj.toString();
//        AcceptData acceptData = new AcceptData();
//        requestPayload = "token=xoxb-263885488724-r8b1U3T2eluu5VzPUtTIKhvY&channel=%23general&text=tetssssss111222";
//        acceptData.setURI(slackBotDomain);
//        acceptData.setRequestMethod("POST");
//        acceptData.setRequestPayload(requestPayload);
        //adaptor.executeSlack(acceptData);
    }
}
