package app;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 19:42
 */
@ApplicationPath("/api")
public class RestAPI extends ResourceConfig {

	public RestAPI() {
		packages("app");
	}
}
