package travel.sample;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import travel.commons.http.RestClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/junit-travel-context.xml","classpath:spring/junit-travel-servlet.xml"})
@WebAppConfiguration
public class RestApiServerSampleControllerTest {
	
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() {
        MockitoAnnotations.initMocks(this); 
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@Test
	@Ignore
	public void testGetAssamblePcProductPrice() throws Exception {
		//get 테스트
		this.mockMvc.perform(get("/assemblePcProductPriceInfo/{basketId}" , 3)
				.param("productId", "10003")
				.param("productId", "10004")
				.param("productId", "10005")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
			).andDo(print())
			.andExpect(status().isOk());		
		
		
		
		//post 테스트
		/*
		this.mockMvc.perform(post("/assemblePcProductPriceInfo/{basketId}" , 3)
							.param("productId", "10003")
							.param("productId", "10004")
							.param("productId", "10005")
							.accept(MediaType.APPLICATION_JSON)
							.contentType(MediaType.APPLICATION_JSON)
							.characterEncoding("UTF-8")
						).andDo(print())
						.andExpect(status().isOk());		
					
		*/
	}
	
	@Inject
	private RestClientService restClientService;
	
	@Test
	@Ignore
	public void testGetAssamblePcProductPriceInfo() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("basketId", "3");
		List<String> list = new ArrayList<String>();
		list.add("1003");
		paramMap.put("productIdList", list);
		
		String params = mapper.writeValueAsString(paramMap);
		
		System.out.println(params);
		
		Map<String, Object> temp = restClientService.restGet("http://localhost:8080/rest-api/assemblePcProductPriceInfo/jsonparam", params, 10000);
		
		System.out.println(temp);
		
	}
	
}
