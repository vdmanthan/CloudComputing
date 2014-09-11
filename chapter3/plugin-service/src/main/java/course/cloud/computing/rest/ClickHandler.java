package course.cloud.computing.rest;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("click")
public class ClickHandler {

	ClickDatabase db = ClickDatabase.getDatabase();

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response handleClick(String URL) {
		try {
			if (db.reportLike(URL)) {
				return Response.status(Status.CREATED).build();
			}else{
				System.out.println("not null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
}
