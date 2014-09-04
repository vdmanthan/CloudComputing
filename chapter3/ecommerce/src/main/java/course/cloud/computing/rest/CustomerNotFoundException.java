package course.cloud.computing.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


public class CustomerNotFoundException extends WebApplicationException {

	public CustomerNotFoundException(String msg) {
		super(Response.status(Status.NOT_FOUND).entity(msg)
				.type("text/plain").build());
	}

}
