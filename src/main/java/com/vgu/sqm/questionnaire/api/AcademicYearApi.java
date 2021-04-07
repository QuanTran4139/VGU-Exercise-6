package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.database.SQLCustomException;
import com.vgu.sqm.questionnaire.resource.AcademicYear;
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

@WebServlet("/api/academicYear")
public class AcademicYearApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(AcademicYearApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private final static String p_AYearID = "yid";

    public AcademicYearApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpAcademicYear();");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("aYearId"); // Attribute name: aYearId

                resources.add(new AcademicYear(id));
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
            int id = json.getJsonNumber(AcademicYearApi.p_AYearID).intValue();
            addResourceToDatabase(new AcademicYear(id));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(String.format("%s must be int", AcademicYearApi.p_AYearID));
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
        if (request.getParameterMap().containsKey(AcademicYearApi.p_AYearID)) {
            try {
                int id = Integer.parseInt(request.getParameter(AcademicYearApi.p_AYearID));
                deleteResourceFromDataBase(id);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    String.format("%s must be an int", AcademicYearApi.p_AYearID));
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(
                String.format("Missing parameter: %s", AcademicYearApi.p_AYearID));
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource)
        throws SQLCustomException, NamingException {
        JsonObject entity = resource.exportResourceJson();
        int id = entity.getJsonNumber(AcademicYear.p_id).intValue();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL AddAcademicYear(?,?);");
            st.setInt(1, id);
            st.registerOutParameter(2, Types.INTEGER);

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

    private void deleteResourceFromDataBase(int AYearID) {
        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st =
                db.prepareCall("CALL DeleteAcademicYear(?,?)");
            st.setInt(1, AYearID);
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
