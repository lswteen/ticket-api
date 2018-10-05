package travel.api.external.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import travel.api.external.dto.*;
import travel.api.external.execution.ErrorDetails;
import travel.api.external.service.ExternalDummyService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * Created by we on 2017. 3. 21..
 * 공통 데이터 및 해우리 더미
 */
@Service
public class ExternalServiceDummyImpl implements ExternalDummyService{
    @Override
    public Ticket orders(HttpTicketRequest request) {
        Ticket ticket = new Ticket();
        ticket.setTotalCount((long) 3);
//=========================================================================================

        List<Option> options = new LinkedList<Option>();

        List<TicketItem> tickets = new LinkedList<TicketItem>();
        TicketItem ticketItem = new TicketItem();
        ticketItem.setTicketCode("1000000001");
        ticketItem.setAmount((long) 10000);
        ticketItem.setStatus("1");
        ticketItem.setValidityStartDate("20170103");
        ticketItem.setValidityEndDate("20170131");
        tickets.add(ticketItem);

        TicketItem ticketItem1 = new TicketItem();
        ticketItem1.setTicketCode("1000000002");
        ticketItem1.setAmount((long) 10000);
        ticketItem1.setStatus("1");
        ticketItem1.setValidityStartDate("20170103");
        ticketItem1.setValidityEndDate("20170131");
        tickets.add(ticketItem1);

        TicketItem ticketItem2 = new TicketItem();
        ticketItem2.setTicketCode("1000000003");
        ticketItem2.setAmount((long) 10000);
        ticketItem2.setStatus("1");
        ticketItem2.setValidityStartDate("20170103");
        ticketItem2.setValidityEndDate("20170131");
        tickets.add(ticketItem2);

        Option option = new Option();
        option.setOptionId("259258546");
        option.setOptionName("선녀와나무꾼 성인");
        option.setOptionCount((long) 3);
        option.setTickets(tickets);
        options.add(option);

        List<TicketItem> ticketssss = new LinkedList<TicketItem>();
        TicketItem ticketItem3 = new TicketItem();
        ticketItem3.setTicketCode("1000000004");
        ticketItem3.setAmount((long) 10000);
        ticketItem3.setStatus("1");
        ticketItem3.setValidityStartDate("20170103");
        ticketItem3.setValidityEndDate("20170131");
        ticketssss.add(ticketItem3);

        TicketItem ticketItem4 = new TicketItem();
        ticketItem4.setTicketCode("1000000005");
        ticketItem4.setAmount((long) 10000);
        ticketItem4.setStatus("1");
        ticketItem4.setValidityStartDate("20170103");
        ticketItem4.setValidityEndDate("20170131");
        ticketssss.add(ticketItem4);

        TicketItem ticketItem5 = new TicketItem();
        ticketItem5.setTicketCode("1000000006");
        ticketItem5.setAmount((long) 10000);
        ticketItem5.setStatus("1");
        ticketItem5.setValidityStartDate("20170103");
        ticketItem5.setValidityEndDate("20170131");
        ticketssss.add(ticketItem5);

        Option optionA = new Option();
        optionA.setOptionId("259258547");
        optionA.setOptionName("선녀와나무꾼 청소년");
        optionA.setOptionCount((long) 3);
        optionA.setTickets(ticketssss);
        options.add(optionA);


        List<TicketItem> ticketssssA = new LinkedList<TicketItem>();
        TicketItem ticketItem6 = new TicketItem();
        ticketItem6.setTicketCode("1000000007");
        ticketItem6.setAmount((long) 10000);
        ticketItem6.setStatus("1");
        ticketItem6.setValidityStartDate("20170103");
        ticketItem6.setValidityEndDate("20170131");
        ticketssssA.add(ticketItem6);

        TicketItem ticketItem7 = new TicketItem();
        ticketItem7.setTicketCode("1000000008");
        ticketItem7.setAmount((long) 10000);
        ticketItem7.setStatus("1");
        ticketItem7.setValidityStartDate("20170103");
        ticketItem7.setValidityEndDate("20170131");
        ticketssssA.add(ticketItem7);

        TicketItem ticketItem8 = new TicketItem();
        ticketItem8.setTicketCode("1000000009");
        ticketItem8.setAmount((long) 10000);
        ticketItem8.setStatus("1");
        ticketItem8.setValidityStartDate("20170103");
        ticketItem8.setValidityEndDate("20170131");
        ticketssssA.add(ticketItem8);

        Option optionB = new Option();
        optionB.setOptionId("259258548");
        optionB.setOptionName("선녀와나무꾼 소인");
        optionB.setOptionCount((long) 3);
        optionB.setTickets(ticketssssA);
        options.add(optionB);

        List<Product> products = new LinkedList<Product>();
        Product product = new Product();
        product.setProductId("1000000015");
        product.setProductName("선녀와나무꾼");
        product.setOptions(options);
        products.add(product);
//=========================================================================================

        List<TicketItem> ticketsA = new LinkedList<TicketItem>();
        TicketItem ticketItemA = new TicketItem();
        ticketItemA.setTicketCode("1000000010");
        ticketItemA.setAmount((long) 10000);
        ticketItemA.setStatus("1");
        ticketItemA.setValidityStartDate("20170103");
        ticketItemA.setValidityEndDate("20170131");
        ticketsA.add(ticketItemA);

        TicketItem ticketItemB = new TicketItem();
        ticketItemB.setTicketCode("1000000011");
        ticketItemB.setAmount((long) 10000);
        ticketItemB.setStatus("1");
        ticketItemB.setValidityStartDate("20170103");
        ticketItemB.setValidityEndDate("20170131");
        ticketsA.add(ticketItemB);

        TicketItem ticketItemC = new TicketItem();
        ticketItemC.setTicketCode("1000000012");
        ticketItemC.setAmount((long) 10000);
        ticketItemC.setStatus("1");
        ticketItemC.setValidityStartDate("20170103");
        ticketItemC.setValidityEndDate("20170131");
        ticketsA.add(ticketItemC);

        List<Option> optionsA = new LinkedList<Option>();
        Option optionC = new Option();
        optionC.setOptionId("256619852");
        optionC.setOptionName("제주민속촌+성산포유람선");
        optionC.setOptionCount((long) 3);
        optionC.setTickets(ticketsA);
        optionsA.add(optionC);

        List<Product> productsV = new LinkedList<Product>();
        Product productA = new Product();
        productA.setProductId("1000000011");
        productA.setProductName("제주민속촌+성산포유람선");
        productA.setOptions(optionsA);
        productsV.add(productA);
//=========================================================================================

        List<TicketItem> ticketsss = new LinkedList<TicketItem>();
        TicketItem ticketItem10 = new TicketItem();
        ticketItem10.setTicketCode("1000000013");
        ticketItem10.setAmount((long) 10000);
        ticketItem10.setStatus("1");
        ticketItem10.setValidityStartDate("20170103");
        ticketItem10.setValidityEndDate("20170131");
        ticketsss.add(ticketItem10);

        TicketItem ticketItem11 = new TicketItem();
        ticketItem11.setTicketCode("1000000014");
        ticketItem11.setAmount((long) 10000);
        ticketItem11.setStatus("1");
        ticketItem11.setValidityStartDate("20170103");
        ticketItem11.setValidityEndDate("20170131");
        ticketsss.add(ticketItem11);

        TicketItem ticketItem22 = new TicketItem();
        ticketItem22.setTicketCode("1000000015");
        ticketItem22.setAmount((long) 10000);
        ticketItem22.setStatus("1");
        ticketItem22.setValidityStartDate("20170103");
        ticketItem22.setValidityEndDate("20170131");
        ticketsss.add(ticketItem22);

        List<Option> optionss = new LinkedList<Option>();
        Option optionAA = new Option();
        optionAA.setOptionId("257751962");
        optionAA.setOptionName("소인국테마파크+카멜리아힐 성인");
        optionAA.setOptionCount((long) 3);
        optionAA.setTickets(ticketsss);
        optionss.add(optionAA);

        List<TicketItem> ticketsss1 = new LinkedList<TicketItem>();
        TicketItem ticketItem111 = new TicketItem();
        ticketItem111.setTicketCode("1000000016");
        ticketItem111.setAmount((long) 10000);
        ticketItem111.setStatus("1");
        ticketItem111.setValidityStartDate("20170103");
        ticketItem111.setValidityEndDate("20170131");
        ticketsss1.add(ticketItem111);

        TicketItem ticketItem1112 = new TicketItem();
        ticketItem1112.setTicketCode("1000000017");
        ticketItem1112.setAmount((long) 10000);
        ticketItem1112.setStatus("1");
        ticketItem1112.setValidityStartDate("20170103");
        ticketItem1112.setValidityEndDate("20170131");
        ticketsss1.add(ticketItem1112);

        TicketItem ticketItem2222 = new TicketItem();
        ticketItem2222.setTicketCode("1000000018");
        ticketItem2222.setAmount((long) 10000);
        ticketItem2222.setStatus("1");
        ticketItem2222.setValidityStartDate("20170103");
        ticketItem2222.setValidityEndDate("20170131");
        ticketsss1.add(ticketItem2222);

        Option optionAAAA = new Option();
        optionAAAA.setOptionId("257751963");
        optionAAAA.setOptionName("소인국테마파크+카멜리아힐 청소년");
        optionAAAA.setOptionCount((long) 3);
        optionAAAA.setTickets(ticketsss1);
        optionss.add(optionAAAA);


        List<TicketItem> ticketsss11 = new LinkedList<TicketItem>();
        TicketItem ticketItem1111 = new TicketItem();
        ticketItem1111.setTicketCode("1000000019");
        ticketItem1111.setAmount((long) 10000);
        ticketItem1111.setStatus("1");
        ticketItem1111.setValidityStartDate("20170103");
        ticketItem1111.setValidityEndDate("20170131");
        ticketsss11.add(ticketItem1111);

        TicketItem ticketItem11122 = new TicketItem();
        ticketItem11122.setTicketCode("1000000020");
        ticketItem11122.setAmount((long) 10000);
        ticketItem11122.setStatus("1");
        ticketItem11122.setValidityStartDate("20170103");
        ticketItem11122.setValidityEndDate("20170131");
        ticketsss11.add(ticketItem11122);

        TicketItem ticketItem22222 = new TicketItem();
        ticketItem22222.setTicketCode("1000000021");
        ticketItem22222.setAmount((long) 10000);
        ticketItem22222.setStatus("1");
        ticketItem22222.setValidityStartDate("20170103");
        ticketItem22222.setValidityEndDate("20170131");
        ticketsss11.add(ticketItem22222);

        Option optionBBBB = new Option();
        optionBBBB.setOptionId("257751965");
        optionBBBB.setOptionName("소인국테마파크+카멜리아힐 소인");
        optionBBBB.setOptionCount((long) 3);
        optionBBBB.setTickets(ticketsss11);
        optionss.add(optionBBBB);

        List<Product> productsC = new LinkedList<Product>();
        Product productC = new Product();
        productC.setProductId("1000000000");
        productC.setProductName("소인국테마파크+카멜리아힐");
        productC.setOptions(optionss);
        productsC.add(productC);

//=========================================================================================
        List<Order> orders = new LinkedList<Order>();
            Order order = new Order();
            order.setOrderCode("10000000010");
            order.setOrderDate("20170413123322");
            order.setOrderName("박초롱");
            order.setOrderMobile("010-4499-1111");
            order.setProducts(products);
            orders.add(order);

            Order orderA = new Order();
            orderA.setOrderCode("10000000011");
            orderA.setOrderDate("20170413123322");
            orderA.setOrderName("박한별");
            orderA.setOrderMobile("010-4499-1111");
            orderA.setProducts(productsV);
            orders.add(orderA);

            Order orderB = new Order();
            orderB.setOrderCode("10000000012");
            orderB.setOrderDate("20170413123322");
            orderB.setOrderName("박초롱");
            orderB.setOrderMobile("010-4499-1111");
            orderB.setProducts(productsC);
            orders.add(orderB);

        ticket.setOrders(orders);
        return ticket;
    }


    @Override
    public Ticket refunds(HttpTicketRequest request) {

        Ticket ticket = new Ticket();
        ticket.setTotalCount((long) 3);
        List<TicketItem> tickets = new ArrayList<TicketItem>();
        for (int i=0; i<3; i++){
            TicketItem ticketItem = new TicketItem();
            ticketItem.setTicketCode("100000000"+i);
            ticketItem.setAmount((long) 10000);
            ticketItem.setRemaining((long) 5000);
            ticketItem.setStatus("1");
            tickets.add(ticketItem);
        }


        List<Option> options = new ArrayList<Option>();
        Option option = new Option();
        option.setOptionId("10001");
        option.setOptionName("벛꽃축제투어버스+양꼬치100개");
        option.setRefundCount((long) 3);
        option.setTickets(tickets);
        options.add(option);

        List<Product> products = new ArrayList<Product>();
        Product product = new Product();
        product.setProductId("WMP0000001");
        product.setProductName("봄프로모션");
        product.setOptions(options);
        products.add(product);

        List<Order> orders = new ArrayList<Order>();
        for (int i = 0; i <3; i++) {
            Order order = new Order();
            order.setOrderCode("1000000001".concat(String.valueOf(i)));
            order.setOrderDate("20170307133322");
            order.setRefundApplyDate("20170307133322");
            order.setRefundCompleteDate("20170307133322");
            order.setOrderName("박초롱");
            order.setOrderMobile("010-4499-1111");
            order.setProducts(products);
            orders.add(order);
        }
        ticket.setOrders(orders);
        return ticket;
    }

    @Override
    public TicketItem ticketInfo(HttpTicketRequest request) {
        TicketItem tickets = new TicketItem();
        tickets.setOrderCode("1000000023");
        tickets.setOrderDate("20170413123322");
        tickets.setOrderName("박초롱");
        tickets.setOrderMobile("01038381234");
        tickets.setProductId("WMP101231211");
        tickets.setOptionName("소인국테마파크+카멜리아힐 청소년");
        tickets.setTicketCode("1000000001");
        tickets.setAmount((long) 19000);
        tickets.setRemaining((long) 0);
        tickets.setStatus("1");
        tickets.setIssueDate("20170421140456");
        tickets.setValidityStartDate("20170325");
        tickets.setValidityEndDate("20170531");
        return tickets;
    }

    @Override
    public Used used(HttpTicketRequest request) {

        List<TicketItem> tickets =  request.getTickets();
        Used used = new Used();
        for (TicketItem item: tickets ) {
//            if("997993499026".equals(item.getTicketCode())){
//                used.setSuccess((long) 1);
//                used.setFailure((long) 0);
//                List<UsedResults> results = new ArrayList<UsedResults>();
//                used.setResults(results);
//            }else if("997038714703".equals(item.getTicketCode())){
//                used.setSuccess((long) 1);
//                used.setFailure((long) 0);
//                List<UsedResults> results = new ArrayList<UsedResults>();
//                used.setResults(results);
//            }else if("997719084859".equals(item.getTicketCode())){
//                used.setSuccess((long) 0);
//                used.setFailure((long) 1);
//                List<UsedResults> results = new ArrayList<UsedResults>();
//                UsedResults usedResults = new UsedResults();
//                usedResults.setTicketCode("997719084859");
//                results.add(usedResults);
//                used.setResults(results);
//            }else{
//                used.setSuccess((long) 1);
//                used.setFailure((long) 0);
//                List<UsedResults> results = new ArrayList<UsedResults>();
//                used.setResults(results);
//            }

                used.setSuccess((long) 1);
                used.setFailure((long) 0);
                List<UsedResults> results = new ArrayList<UsedResults>();
                used.setResults(results);
//                used.setSuccess("0");
//                used.setFailure("1");
//                List<UsedResults> results = new ArrayList<UsedResults>();
//                UsedResults usedResults = new UsedResults();
//                usedResults.setTicketCode("997719084859");
//                results.add(usedResults);
//                used.setResults(results);
        }
        return used;
    }

    @Override
    public Used singleUsed(HttpTicketRequest request) {

        List<TicketItem> tickets =  request.getTickets();
        Used used = new Used();
        for (TicketItem item: tickets ) {
            used.setSuccess((long) 1);
            used.setFailure((long) 0);
            List<UsedResults> results = new ArrayList<UsedResults>();
            used.setResults(results);
        }
        return used;
    }

    @Override
    public Used multiUsed(HttpTicketRequest request) {

        List<TicketItem> tickets =  request.getTickets();
        Used used = new Used();
        for (TicketItem item: tickets ) {
            used.setSuccess((long) 1);
            used.setFailure((long) 0);
            List<UsedResults> results = new ArrayList<UsedResults>();
            used.setResults(results);
        }
        return used;
    }

    @Override
    public Used reservation(HttpTicketRequest request) {
        //3.Json Response
        Used used = new Used();
        used.setSuccess((long)3);
        used.setFailure((long)2);
        List<UsedResults> results = new ArrayList<UsedResults>();
        UsedResults item = new UsedResults();
        item.setTicketCode("123123");
        results.add(item);
        item.setTicketCode("123123");
        results.add(item);
        used.setResults(results);
        return used;
    }

    @Override
    public HttpStatus usedToCancel(HttpTicketRequest request) {
        HttpStatus status = HttpStatus.OK;
        if("997822719886".equals(request.getTicketCode())){
            status = HttpStatus.NOT_FOUND;
        }
        return status;
    }

    @Override
    public Coupon coupon(HttpTicketRequest request) {
        Coupon coupon = new Coupon();
        String productId = request.getCoupon().getProducts().get(0).getProductId();

        if("3250002582".equals(productId)){
            coupon.setOrderCode("52344235");
            coupon.setOrderDate("20170307121213");
            coupon.setOrderName("설현");
            coupon.setOrderMobile("010-1234-1234");
            List<Product> products = new LinkedList<Product>();
            Product item = new Product();
            item.setProductId("3250002582");
            List<Option> options = new LinkedList<Option>();
            Option optionItem = new Option();
            optionItem.setOptionId("TEST001");
            List<TicketItem> tickets = new LinkedList<TicketItem>();
            TicketItem ticketItem = new TicketItem();
            Random generator = new Random();
            ticketItem.setPinNo("WE-TNC-".concat(String.valueOf(generator.nextInt(10000))));
            tickets.add(ticketItem);
            optionItem.setTickets(tickets);
            options.add(optionItem);
            item.setOptions(options);
            products.add(item);
            coupon.setProducts(products);
        }else if("3250002586".equals(productId)){
            coupon.setOrderCode("52344234");
            coupon.setOrderDate("20170307121213");
            coupon.setOrderName("설현");
            coupon.setOrderMobile("010-1234-1234");
            List<Product> products = new LinkedList<Product>();
            Product item = new Product();
            item.setProductId("3250002586");
            List<Option> options = new LinkedList<Option>();
            Option optionItem = new Option();
            optionItem.setOptionId("PNC-001");
            List<TicketItem> tickets = new LinkedList<TicketItem>();
            TicketItem ticketItem = new TicketItem();
            Random generator = new Random();
            ticketItem.setPinNo("WE-TNC-".concat(String.valueOf(generator.nextInt(10000))));
            tickets.add(ticketItem);
            optionItem.setTickets(tickets);
            options.add(optionItem);
            item.setOptions(options);
            products.add(item);
            coupon.setProducts(products);
        }else if("3250002587".equals(productId)){
            coupon.setOrderCode("52344233");
            coupon.setOrderDate("20170307121213");
            coupon.setOrderName("설현");
            coupon.setOrderMobile("010-1234-1234");
            List<Product> products = new LinkedList<Product>();
            Product item = new Product();
            item.setProductId("3250002587");
            List<Option> options = new LinkedList<Option>();
            Option optionItem = new Option();
            optionItem.setOptionId("WMP-001");
            List<TicketItem> tickets = new LinkedList<TicketItem>();
            TicketItem ticketItem = new TicketItem();
            Random generator = new Random();
            ticketItem.setPinNo("WE-TNC-".concat(String.valueOf(generator.nextInt(10000))));
            tickets.add(ticketItem);
            optionItem.setTickets(tickets);
            options.add(optionItem);
            item.setOptions(options);
            products.add(item);
            coupon.setProducts(products);
        }
//        ErrorDetails errorDetails = new ErrorDetails();
//        errorDetails.setCode("9005");
//        errorDetails.setService("22807822");
//        errorDetails.setId("404");
//        errorDetails.setMessage("error error");
        return coupon;
    }

    @Override
    public HttpStatus orderProcess(HttpTicketRequest request) {
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus couponDiscarded(HttpTicketRequest request) {
        return HttpStatus.OK;
    }

    @Override
    public Coupon orderMapping(HttpTicketRequest request) {
        Coupon coupon = new Coupon();
        coupon.setOrderCode("511B301-00001");

        List<Product> products = new LinkedList<Product>();
        Product item = new Product();
        item.setProductId("WMP10000001");
        List<Option> options = new LinkedList<Option>();
        Option optionItem = new Option();
        optionItem.setOptionId("WMO10000001");

        List<TicketItem> tickets = new LinkedList<TicketItem>();

        TicketItem ticketItem = new TicketItem();
        ticketItem.setPinNo("TI2BDE-00001");
        ticketItem.setTicketCode("WMP-00001");
        tickets.add(ticketItem);

        TicketItem itemA = new TicketItem();
        itemA.setPinNo("TI2BDE-00001");
        itemA.setTicketCode("WMP-00002");
        tickets.add(itemA);

        TicketItem itemB = new TicketItem();
        itemB.setPinNo("TI2BDE-00001");
        itemB.setTicketCode("WMP-00003");
        tickets.add(itemB);

        optionItem.setTickets(tickets);
        options.add(optionItem);
        item.setOptions(options);
        products.add(item);
        coupon.setProducts(products);
        return coupon;
    }

    @Override
    public HttpStatus send(HttpTicketRequest request) {
        return HttpStatus.OK;
    }
}
