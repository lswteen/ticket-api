/*package travel;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


import org.apache.logging.log4j.*;

*//**
 * Created by we on 2017. 8. 31..
 *//*
public class TestController {
	
	private static final Logger logger = LogManager.getLogger(TestController.class);
	
    public static class loggingModel
    {
        private String serviceId;
        private long currentTime;
        private String currentUrl;
        private String httpStatus;
        private long methodTime;
        private long assetTime;
        private long extendTime;
        private String consumerId;
        private String responseData;
        private String requestData;
        private external external;

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public long getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(long currentTime) {
            this.currentTime = currentTime;
        }

        public String getCurrentUrl() {
            return currentUrl;
        }

        public void setCurrentUrl(String currentUrl) {
            this.currentUrl = currentUrl;
        }

        public String getHttpStatus() {
            return httpStatus;
        }

        public void setHttpStatus(String httpStatus) {
            this.httpStatus = httpStatus;
        }

        public long getMethodTime() {
            return methodTime;
        }

        public void setMethodTime(long methodTime) {
            this.methodTime = methodTime;
        }

        public long getAssetTime() {
            return assetTime;
        }

        public void setAssetTime(long assetTime) {
            this.assetTime = assetTime;
        }

        public long getExtendTime() {
            return extendTime;
        }

        public void setExtendTime(long extendTime) {
            this.extendTime = extendTime;
        }

        public String getConsumerId() {
            return consumerId;
        }

        public void setConsumerId(String consumerId) {
            this.consumerId = consumerId;
        }

        public String getResponseData() {
            return responseData;
        }

        public void setResponseData(String responseData) {
            this.responseData = responseData;
        }

        public String getRequestData() {
            return requestData;
        }

        public void setRequestData(String requestData) {
            this.requestData = requestData;
        }

        public external getExternal() {
            return external;
        }

        public void setExternal(external external) {
            this.external = external;
        }

        public static class external
        {
            private String restKey;
            private String companyId;
            public String getRestKey() {
                return restKey;
            }
            public void setRestKey(String restKey) {
                this.restKey = restKey;
            }
            public String getCompanyId() {
                return companyId;
            }
            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }
        }
    }

    private static volatile TransportClient client;
    public TransportClient getClient() {
        // TODO Auto-generated method stub
        if (client == null) {
            synchronized (TestController.class) {
                Settings settings = Settings.builder().put("cluster.name", "mgw_cluster").build();
                @SuppressWarnings("unchecked")
                PreBuiltTransportClient transport = new PreBuiltTransportClient(settings);
                try {
                    client = transport.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.102.1.153"), 9300));
                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return client;
    }

    public IndexRequestBuilder createDocument(loggingModel entity) {
        // TODO Auto-generated method stub

        try {
            return client.prepareIndex(".commonlog-2017.07.11", "api-log").setSource(jsonBuilder()
                    .startObject()
                    .field("serviceId", entity.getServiceId())
                    .startObject("external")
                    .field("restKey", entity.getExternal().getRestKey())
                    .field("companyId", entity.getExternal().getCompanyId())
                    .endObject()
                    .field("currentTime", entity.getCurrentTime())
                    .field("currentUrl", entity.getCurrentUrl())
                    .field("httpStatus", entity.getHttpStatus())
                    .field("methodTime", entity.getMethodTime())
                    .field("assetTime", entity.getAssetTime())
                    .field("extendTime", entity.getExtendTime())
                    .field("consumerId", entity.getConsumerId())
                    .field("responseData", entity.getResponseData())
                    .field("requestData", entity.getRequestData())
                    .endObject());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public void execute()
    {
    	logger.debug("debug started...");
        *//*************************initialize client**********************************//*
        this.getClient();
        *//*************************initialize client**********************************//*

        *//*************************set mock data**********************************//*
        long exampleTime = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").
                format(new Date(System.currentTimeMillis())));
        loggingModel model = new loggingModel();
        model.setServiceId("wmps001");
        model.setCurrentTime(exampleTime);
        model.setCurrentUrl("/item/view?userid=2015060074");
        model.setHttpStatus("500");
        model.setMethodTime(exampleTime);
        model.setAssetTime(exampleTime);
        model.setExtendTime(exampleTime);
        loggingModel.external ex = new loggingModel.external();
        ex.setRestKey("f61794c2-d3c9-4a38-ae5e-550c92579785");
        ex.setCompanyId("aladin");
        model.setExternal(ex);
        model.setConsumerId("API001");
        model.setResponseData("{ \"result\" : 500, \"error\" : \"에라!!!!\" }");
        model.setRequestData("{ \"userid\" : \"2015060074\" }");
        *//*************************set mock data**********************************//*

        *//*************************index to es**********************************//*
        IndexRequestBuilder builder = client.prepareIndex();
        builder = this.createDocument(model);
        builder.execute(new ActionListener<IndexResponse>() {
            
            @Override
            public void onResponse(IndexResponse response) {
            	System.out.println("index--------->"+response.getIndex());
            }

			@Override
			public void onFailure(Throwable arg0) {
				System.out.println(arg0.getMessage());
			}
        });
        *//*************************index to es**********************************//*
    }
    
    public static void main(String args[]){
        TestController tc = new TestController();
        tc.execute();
    }
}
*/