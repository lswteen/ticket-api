package travel.api.external.service;

import org.springframework.http.HttpStatus;
import travel.api.external.dto.*;
import travel.api.external.execution.ErrorDetails;

/**
 * Created by we on 2017. 5. 17..
 */
public interface ExternalDummyService {
    public Ticket orders(HttpTicketRequest request);
    public Ticket refunds(HttpTicketRequest request);
    public TicketItem ticketInfo(HttpTicketRequest request);
    public Used used(HttpTicketRequest request);
    public Used singleUsed(HttpTicketRequest request);
    public Used multiUsed(HttpTicketRequest request);
    public Used reservation(HttpTicketRequest request);
    public HttpStatus usedToCancel(HttpTicketRequest request);
    public Coupon coupon(HttpTicketRequest request);
    public HttpStatus orderProcess(HttpTicketRequest request);
    public HttpStatus couponDiscarded(HttpTicketRequest request);
    public Coupon orderMapping(HttpTicketRequest request);
    public HttpStatus send(HttpTicketRequest request);
}
