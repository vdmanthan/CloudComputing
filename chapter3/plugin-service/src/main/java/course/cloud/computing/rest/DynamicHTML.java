package course.cloud.computing.rest;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("button")
public class DynamicHTML {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getDivContents(){
		return "<button type='button' name='tracker-button' onclick='reportClick()'> Like! </button>";
	}

}
