package com.vgu.sqm.questionnaire;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/example")
public class ExampleResource {
	@GET
	public String getMessage()    {
		return "Hello World!";
	}
}
