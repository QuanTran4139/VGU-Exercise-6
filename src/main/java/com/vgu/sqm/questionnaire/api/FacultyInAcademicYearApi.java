package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.database.SQLCustomException;
import com.vgu.sqm.questionnaire.resource.FacultyInAcademicYear;
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

@WebServlet("/api/facultyInAcademicYear")
public class FacultyInAcademicYearApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(FacultyInAcademicYearApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private final static String p_FacultyID = "fid";
    private final static String p_AYearID = "yid";

    public FacultyInAcademicYearApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpFacultyInAcademicYear();");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String fId = rs.getString("FacultyID"); // Attribute name: FacultyID
                int aId = rs.getInt("AYearId"); // Attribute name: AYearID

                resources.add(new FacultyInAcademicYear(fId, aId));
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
            String fid = json.getJsonString(p_FacultyID).getString();
            int yid = json.getJsonNumber(p_AYearID).intValue();
            if (FacultyInAcademicYear.checkParametersAreValid(fid, yid)) {
                addResourceToDatabase(new FacultyInAcademicYear(fid, yid));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "One or more parameters is invalid: %s, %s".format(p_FacultyID, p_AYearID));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Malformed JSON request body");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getParameterMap().containsKey(p_FacultyID)
            && request.getParameterMap().containsKey(p_AYearID)) {
            try {
                String fid = request.getParameter(p_FacultyID);
                int yid = Integer.parseInt(request.getParameter(p_AYearID));
                deleteResourceFromDataBase(fid, yid);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print("%s must be an int".format(p_AYearID));
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Missing parameter: %s, %s".format(p_FacultyID, p_AYearID));
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource)
        throws SQLCustomException, SQLException, NamingException {
        // TODO
    }

    private void deleteResourceFromDataBase(String FacultyID, int AYearID) {
        // TODO
    }
}
