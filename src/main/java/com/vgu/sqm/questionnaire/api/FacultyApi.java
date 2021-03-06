package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.database.SQLCustomException;
import com.vgu.sqm.questionnaire.resource.Faculty;
import com.vgu.sqm.questionnaire.resource.Resource;
import com.vgu.sqm.questionnaire.utils.JsonUtils;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/faculty")
public class FacultyApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(FacultyApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private final static String p_FacultyID = "id";
    private final static String p_FacultyName = "name";

    public FacultyApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpFaculty()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("FacultyID"); // Attribute name FacultyID
                String name = rs.getString("FacultyName"); // Attribute name FacultyName

                resources.add(new Faculty(id, name));
            }
            LOGGER.log(Level.INFO, "Getting info from database successfully.");
            db.close();
        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }
        return resources;
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            JsonObject json = JsonUtils.extractJsonRequestBody(request);
            String id = json.getJsonString(FacultyApi.p_FacultyID).getString();
            String name = json.getJsonString(FacultyApi.p_FacultyName).getString();
            if (Faculty.checkParametersAreValid(id, name)) {
                addResourceToDatabase(new Faculty(id, name));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    String.format("One or more parameters is invalid: %s, %s",
                        FacultyApi.p_FacultyID, FacultyApi.p_FacultyName));
            }
        } catch (SQLCustomException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(e);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Malformed JSON request body");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getParameterMap().containsKey(FacultyApi.p_FacultyID)) {
            deleteResourceFromDataBase(request.getParameter(FacultyApi.p_FacultyID));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Missing parameter: %s");
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource)
        throws SQLCustomException, NamingException {
        JsonObject entity = resource.exportResourceJson();
        String fId = entity.getJsonString(Faculty.p_FacultyID).toString();
        String fName = entity.getJsonString(Faculty.p_FacultyName).toString();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL AddFaculty(?,?,?);");
            st.setString(1, fId);
            st.setString(2, fName);
            st.registerOutParameter(3, Types.INTEGER);

            st.execute();

            int status = st.getInt(3);
            LOGGER.log(Level.INFO, "Status: " + status);
            if (status != 200) {
                throw new SQLCustomException(status);
            }

        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }
    }

    private void deleteResourceFromDataBase(String FacultyID) {
        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st =
                    db.prepareCall("CALL DeleteFaculty(?,?)");
            st.setString(1, FacultyID);
            st.registerOutParameter(2,Types.INTEGER);

            st.execute();

            int status = st.getInt(2);
            LOGGER.log(Level.INFO, "Status: " + status);

            if (status != 200) {
                throw new SQLCustomException(status);
            }

        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }
    }
}
