package travel.api.Internel.service;

import travel.api.Internel.domain.HttpInternelRequest;
import travel.api.Internel.dto.OptionIssued;
import travel.api.external.adaptor.AcceptData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by we on 2017. 4. 19..
 */
public interface InternelCouponService {
    public List<OptionIssued> generateCoupon(HttpInternelRequest requset, HttpServletRequest request);
    public AcceptData send(HttpInternelRequest requset);
    public AcceptData couponDiscarded(HttpInternelRequest request);
    public AcceptData coupon(HttpInternelRequest request);
}
