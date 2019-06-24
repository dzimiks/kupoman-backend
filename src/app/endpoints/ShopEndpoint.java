package app.endpoints;

import app.models.Shop;
import app.services.ShopService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:50
 */
@Path("/shops")
public class ShopEndpoint {

	private ShopService shopService;

	public ShopEndpoint() {
		this.shopService = new ShopService();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> get() {
		return shopService.getShops();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Shop getByID(@PathParam("id") String id) {
		return shopService.getShopByID(id);
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {
		shopService.deleteShop(id);
		return Response.ok().build();
	}
}
