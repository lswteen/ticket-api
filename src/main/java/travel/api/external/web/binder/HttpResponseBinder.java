package travel.api.external.web.binder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import travel.api.Internel.dao.InternelDao;
import travel.api.external.adaptor.AcceptData;
import travel.api.external.constants.ResponseCode;
import travel.api.external.domain.*;
import travel.api.external.dto.*;
import travel.api.external.exception.TicketApiException;
import travel.api.external.execution.http.HttpProtocolMapper;

import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * Created by we on 2017. 2. 24..
 */
@Component
public class HttpResponseBinder {

    public static ResponseEntity<?> toResponseEntity(HttpStatus code) {
        ResponseEntity<?> responseEntity = new ResponseEntity<String>(code.getReasonPhrase(), code);
        return responseEntity;
    }

    @Autowired
    private InternelDao internelDao;

//    private Coupon filterOptoin(Coupon coupon) {
//        //Request 상품 객체
//        List<Product> products = coupon.getProducts();
//        //상품 에 있는 하위 노드를 1차원 Set Collection
//        Set<Option> optionSet = products.stream()
//                .flatMap(e -> e.getOptions().stream())
//                .collect(toSet());
//        Map<String,String> optionMap = new HashMap();
//        optionSet.forEach(
//            o -> {
//                String companyCode = internelDao.getWeMakePriceOption(o.getOptionId());
//                if(StringUtils.isNotEmpty(companyCode)){
//                    optionMap.put(o.getOptionId(),companyCode);
//                }
//            }
//        );
//        //전달 Response  옵션을 변경
//        products.stream()
//                .forEach(p -> {
//                    List<Option> newOptionList = new ArrayList<>();
//                    p.getOptions().forEach(o -> {
//                        Option newOption = new Option();
//                        newOption.setOptionId(optionMap.get(o.getOptionId()));
//                        newOption.setTickets(o.getTickets());
//                        newOptionList.add(newOption);
//                    });
//                    p.setOptions(newOptionList);
//                });
//        return coupon;
//
//    }

    public <T> ResponseEntity<T> toResponseEntity(HttpStatus code, AcceptData acceptData) {
        ResponseEntity<T> responseEntity =null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try{
            if(null != acceptData.getHttpStatus()){
                code = acceptData.getHttpStatus();
            }

            if("API_EIS_001".equals(acceptData.getConsumer()) || "API_EIS_002".equals(acceptData.getConsumer())){
                Ticket ticket = objectMapper.readValue( acceptData.getPayload(),Ticket.class);
                responseEntity = new ResponseEntity<T>((T) ticket, code);
            }
            if("API_EIS_003".equals(acceptData.getConsumer())){
                TicketItem ticketItem = objectMapper.readValue( acceptData.getPayload(),TicketItem.class);
                responseEntity = new ResponseEntity<T>((T) ticketItem, code);
            }
            if("API_EIS_004".equals(acceptData.getConsumer())
                || "API_EIS_005".equals(acceptData.getConsumer())
                || "API_EIS_011".equals(acceptData.getConsumer())
                || "API_EIS_012".equals(acceptData.getConsumer())

            ){
                //List<> list = Options.ofNullable(acceptData.getPayload()).ofGet(ArrayList::ne w);
                Used used = objectMapper.readValue( acceptData.getPayload(),Used.class);
                responseEntity = new ResponseEntity<T>((T) used,code);
            }
            if(
               "API_IIS_003".equals(acceptData.getConsumer()) ||
               "API_IIS_002".equals(acceptData.getConsumer())
            ){
                if(HttpStatus.OK.equals(code)){
                    responseEntity = new ResponseEntity<T>(code);
                }else{
                    throw new TicketApiException(ResponseCode.SERVICE_ERROR,acceptData.getPayload(),acceptData);
                }
            }
            if("API_IIS_004".equals(acceptData.getConsumer())){
                if(HttpStatus.OK.equals(code)){
                    Coupon coupon = objectMapper.readValue(acceptData.getPayload(),Coupon.class);
                    //filterOptoin(coupon);
                    responseEntity = new ResponseEntity<T>((T) coupon,code);
                }else{
                    throw new TicketApiException(ResponseCode.SERVICE_ERROR,acceptData.getPayload(),acceptData);
                }
            }
            if(
                "API_EIS_006".equals(acceptData.getConsumer()) ||
                "API_EIS_008".equals(acceptData.getConsumer())
            ){
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(acceptData.getPayload());
                JSONObject jsonObject = (JSONObject) obj;
                if(200 == (long)jsonObject.get("result")){
                    responseEntity = new ResponseEntity<T>(code);
                }else{
                    String errorCode = (String) jsonObject.get("errorCode");
                    throw new TicketApiException(ResponseCode.INTERNAL_SERVER_ERROR,errorCode,acceptData);
                }
            }
        } catch (IOException e) {
            throw new TicketApiException(ResponseCode.SERVICE_ERROR,acceptData.getPayload(),acceptData);
        } catch(NullPointerException e){
            if(null != acceptData.getPayload()){
                throw new TicketApiException(ResponseCode.INTERNAL_SERVER_ERROR,acceptData.getPayload(),acceptData);
            }else{
                throw new TicketApiException(ResponseCode.INTERNAL_SERVER_ERROR,"Is Null Response Payload ",acceptData);
            }
        } catch (ParseException e) {
            throw new TicketApiException(ResponseCode.SERVICE_ERROR,acceptData.getPayload(),acceptData);
        } catch(TooManyResultsException e){
            throw new TicketApiException(ResponseCode.INTERNAL_SERVER_ERROR,"대행사 옵션상품은 중복으로 등록될수 없습나다.",acceptData);
        }
        return responseEntity;
    }

    public static <T> ResponseEntity<T> toResponseEntity(HttpStatus code, T payload) {
        ResponseEntity<T> responseEntity = new ResponseEntity<T>(payload, code);
        return responseEntity;
    }

    public static ResponseEntity<?> toResponseEntity(TicketResponse<?> response, boolean logging) {
        HttpStatus httpStatus = HttpProtocolMapper.getHttpStatus(response.getResponseCode());
        ResponseEntity<?> responseEntity = new ResponseEntity<TicketResponse<?>>(response, responseHeader(response.getTransfer()),  httpStatus);

        if(logging)
            // logging
            traceLogging(response);

        return responseEntity;
    }

    public static <T extends AbstractObject> ResponseEntity<CollectionResponse<T>> toResponseEntity(HttpStatus code, CollectionResponse<T> collection) {
        ResponseEntity<CollectionResponse<T>> responseEntity = new ResponseEntity<CollectionResponse<T>>(collection, code);
        return responseEntity;
    }

    public static ResponseEntity<?> toResponseEntity(TicketResponse<?> response) {
        return toResponseEntity(response, true);
    }

    public static ResponseEntity<?> toResponseEntity(List<TicketResponse<?>> response) {
        AbstractTransferObject forLogging = response.size() > 0 ? response.get(0).getTransfer() : null;
        int failed = 0;
        for (TicketResponse<?> imsResponse : response) {
            // logging
            traceLogging(imsResponse);
            if(imsResponse.getResponseCode().getCode().intValue() != ResponseCode.SUCCESS.getCode().intValue())
                failed ++;

        }

        int total = response.size();
        int success = total - failed;

        TicketCollectionResponse collectionResponse = new TicketCollectionResponse();
        collectionResponse.setTotal(total);
        collectionResponse.setSuccess(success);
        collectionResponse.setFailed(failed);

        ResponseEntity<?> responseEntity = new ResponseEntity<TicketCollectionResponse>(collectionResponse, responseHeader(forLogging), HttpStatus.CREATED);
        return responseEntity;
    }


    private static <T> void traceLogging(TicketResponse<T> response){
        AbstractTransferObject transfer = response.getTransfer();
    }


    private static HttpHeaders responseHeader(AbstractTransferObject transfer){
        HttpHeaders headers = new HttpHeaders();
        headers.add("requestId", transfer.getRequestId());
        return headers;
    }
}
