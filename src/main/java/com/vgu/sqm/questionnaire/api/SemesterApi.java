package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.database.SQLCustomException;
import com.vgu.sqm.questionnaire.resource.Resource;
import com.vgu.sqm.questionnaire.resource.Semester;
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

@WebServlet("/api/semester")
public class SemesterApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(SemesterApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private final static String p_SemesterID = "sid";
    private final static String p_AYearID = "yid";

    public SemesterApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpSemester();");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String sId = rs.getString("SemesterID"); // Attribute name: SemesterID
                int aId = rs.getInt("AYearID"); // Attribute name: AYearID

                resources.add(new Semester(sId, aId));
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
            String SemesterID = json.getJsonString(SemesterApi.p_SemesterID).getString();
            int AYearID = json.getJsonNumber(SemesterApi.p_AYearID).intValue();
            if (Semester.checkParametersAreValid(SemesterID, AYearID)) {
                addResourceToDatabase(new Semester(SemesterID, AYearID));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    String.format("One or more parameters is invalid: %s, %s",
                        SemesterApi.p_SemesterID, SemesterApi.p_AYearID));
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(String.format("%s must be int", SemesterApi.p_AYearID));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Malformed JSON request body");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getParameterMap().containsKey(SemesterApi.p_SemesterID)) {
            deleteResourceFromDataBase(request.getParameter(SemesterApi.p_SemesterID));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(
                String.format("Missing parameter: %s", SemesterApi.p_SemesterID));
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource)
        throws SQLException, NamingException {
        JsonObject entity = resource.exportResourceJson();
        int aYearId = entity.getJsonNumber(Semester.p_AYearID).intValue();
        String sId = entity.getJsonString(Semester.p_SemesterID).toString();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL AddSemester(?,?,?);");
            st.setInt(1, aYearId);
            st.setString(2, sId);
            st.registerOutParameter(3, Types.INTEGER);

            st.execute();

            int status = st.getInt(3);
            LOGGER.log(Level.INFO, "Status: " + status);
            if (status != 200) {
                throw new SQLCustomException(status, this.getClass().getName());
            }
            db.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE,e.toString());
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE,e.toString());
        }
    }

    private void deleteResourceFromDataBase(String SemesterID) {
        // TODO
    }
}
