package app.endpoints;

import app.models.User;
import app.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:50
 */
@Path("/users")
public class UserEndpoint {

	private UserService userService;

	public UserEndpoint() {
		this.userService = new UserService();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> get() {
		return userService.getUsers();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User add(User user) {
		return userService.addUser(user);
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {
		userService.deleteUser(id);
		return Response.ok().build();
	}
}
