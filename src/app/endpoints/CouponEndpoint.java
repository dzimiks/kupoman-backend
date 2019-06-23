package app.endpoints;

import app.models.Coupon;
import app.services.CouponService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:50
 */
@Path("/coupons")
public class CouponEndpoint {

	private CouponService couponService;

	public CouponEndpoint() {
		this.couponService = new CouponService();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Coupon> get() {
		return couponService.getCoupons();
	}

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Coupon add(Coupon coupon) {
//		return couponService.addCoupon(coupon);
//	}
//
//	@DELETE
//	@Path("{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response delete(@PathParam("id") Integer id) {
//		couponService.deleteCoupon(id);
//		return Response.ok().build();
//	}
}
