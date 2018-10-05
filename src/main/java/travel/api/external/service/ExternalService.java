package travel.api.external.service;

import travel.api.external.adaptor.AcceptData;
import travel.api.external.dto.HttpTicketRequest;

/**
 * Created by we on 2017. 3. 16..
 */
public interface ExternalService {
    AcceptData orders(HttpTicketRequest request);
    AcceptData refunds(HttpTicketRequest request);
    AcceptData ticketInfo(HttpTicketRequest request);
    AcceptData used(HttpTicketRequest request);
    AcceptData singleUsed(HttpTicketRequest request);
    AcceptData multiUsed(HttpTicketRequest request);
    AcceptData reservation(HttpTicketRequest request);
    AcceptData usedToCancel(HttpTicketRequest request);
    //public AcceptData orderMapping(HttpTicketRequest httpTicketRequest)
}
