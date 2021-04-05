package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.database.SQLCustomException;
import com.vgu.sqm.questionnaire.resource.ProgramInFacultyInAcademicYear;
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

@WebServlet("/api/programInFacultyInAcademicYear")
public class ProgramInFacultyInAcademicYearApi extends ResourceApi {
    private final static Logger LOGGER =
        Logger.getLogger(ProgramInFacultyInAcademicYearApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private final static String p_ProgramID = "pid";
    private final static String p_FacultyID = "fid";
    private final static String p_AYearID = "yid";

    public ProgramInFacultyInAcademicYearApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpProgramInFacultyInAcademicYear();");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String pId = rs.getString("ProgramID"); // Attribute name: ProgramID
                String fId = rs.getString("FacultyID"); // Attribute name: FacultyID
                int yID = rs.getInt("AYearID"); // Attribute name: AYearID

                resources.add(new ProgramInFacultyInAcademicYear(pId, fId, yID));
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
            String ProgramID =
                json.getJsonString(ProgramInFacultyInAcademicYearApi.p_ProgramID).getString();
            String FacultyID =
                json.getJsonString(ProgramInFacultyInAcademicYearApi.p_FacultyID).getString();
            int AYearID =
                json.getJsonNumber(ProgramInFacultyInAcademicYearApi.p_AYearID).intValue();
            if (ProgramInFacultyInAcademicYear.checkParametersAreValid(
                    ProgramID, FacultyID, AYearID)) {
                addResourceToDatabase(
                    new ProgramInFacultyInAcademicYear(ProgramID, FacultyID, AYearID));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    String.format("One or more parameters is invalid: %s, %s, %s",
                        ProgramInFacultyInAcademicYearApi.p_ProgramID,
                        ProgramInFacultyInAcademicYearApi.p_FacultyID,
                        ProgramInFacultyInAcademicYearApi.p_AYearID));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Malformed JSON request body");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getParameterMap().containsKey(ProgramInFacultyInAcademicYearApi.p_ProgramID)
            && request.getParameterMap().containsKey(ProgramInFacultyInAcademicYearApi.p_FacultyID)
            && request.getParameterMap().containsKey(ProgramInFacultyInAcademicYearApi.p_AYearID)) {
            try {
                String program =
                    request.getParameter(ProgramInFacultyInAcademicYearApi.p_ProgramID);
                String faculty =
                    request.getParameter(ProgramInFacultyInAcademicYearApi.p_FacultyID);
                int year = Integer.parseInt(
                    request.getParameter(ProgramInFacultyInAcademicYearApi.p_AYearID));
                deleteResourceFromDataBase(program, faculty, year);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "%s must be int".format(ProgramInFacultyInAcademicYearApi.p_AYearID));
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Missing parameters: %s, %s, %s".format(
                ProgramInFacultyInAcademicYearApi.p_ProgramID,
                ProgramInFacultyInAcademicYearApi.p_FacultyID,
                ProgramInFacultyInAcademicYearApi.p_AYearID));
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource)
        throws SQLCustomException, SQLException, NamingException {
        // TODO
    }

    private void deleteResourceFromDataBase(String ProgramID, String FacultyID, int AYearID) {
        // TODO
    }
}
