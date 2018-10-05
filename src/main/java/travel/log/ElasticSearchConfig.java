package travel.log;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;

//@Configuration
public class ElasticSearchConfig{

    @Value("${elk.domain}")
    private String elkDomain;

    @Value("${elk.cluster}")
    private String elkCluster;

//	@Bean
	public TransportClient connectionFactory()
    {
	    TransportClient client = null;
	    try {
            Settings settings = Settings.builder().put("cluster.name", elkCluster).build();
            PreBuiltTransportClient transport = new PreBuiltTransportClient(settings);
            client = transport.addTransportAddress(
                    new InetSocketTransportAddress(InetAddress.getByName(elkDomain), 9300)
            );
        } catch (UnknownHostException e) {
           e.printStackTrace();
        }

        return client;
	}

}