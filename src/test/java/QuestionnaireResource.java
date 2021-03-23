package test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/programs")
public class QuestionnaireResource {
	
	@Path("/count")
	@GET
	public Response getProgramsCount() throws SQLException, NamingException   {
		
		Connection db = Configuration.getAcademiaConnection();
		int statusCode;
		
		try {
			
//			PreparedStatement st = db.prepareC(
//					"CALL createAcademicYear('1', @statusCode);"
//					);
//			ResultSet rs = st.executeQuery();
			
			try (CallableStatement cstmt = db.prepareCall("CALL GetTotalClassesSize(2005,'WS01' ,'Computer And Engineering', 'Electrical Engineering' , 'Calculus','1', ?);");){
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER);
				cstmt.execute();
				statusCode = cstmt.getInt(1);
			}
//			try (CallableStatement cstmt = db.prepareCall("CALL createAcademicYear('1', ?);");){
//				cstmt.registerOutParameter(1,java.sql.Types.INTEGER);
//				cstmt.execute();
//				statusCode = cstmt.getInt(1);
//			}

			
			
//			while (rs.next()) {
//				name = rs.getString("ClassId");
//				System.out.println(name);
//			}
			
			
		
		JsonObject entry = Json.createObjectBuilder()
				.add("status", statusCode).build();
				
		return Response.ok().entity(entry.toString()).build();
		
		}
		finally {
			db.close();
		}
	}
}