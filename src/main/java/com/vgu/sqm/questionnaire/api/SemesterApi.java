package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
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
                String sId = rs.getString(1); // Attribute name: SemesterID
                int aId = rs.getInt(2); // Attribute name: AYearID

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
            String SemesterID = json.getJsonString(p_SemesterID).getString();
            int AYearID = json.getJsonNumber(p_AYearID).intValue();
            if (Semester.checkParametersAreValid(SemesterID, AYearID)) {
                addResourceToDatabase(new Semester(SemesterID, AYearID));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "Invalid parameters: %s, %s".format(p_SemesterID, p_AYearID));
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("%s must be int".format(p_AYearID));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Malformed JSON request body");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getParameterMap().containsKey(p_SemesterID)) {
            deleteResourceFromDataBase(request.getParameter(p_SemesterID));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Missing parameter: %s".format(p_SemesterID));
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource) {
        // TODO
    }

    private void deleteResourceFromDataBase(String SemesterID) {
        // TODO
    }
}
