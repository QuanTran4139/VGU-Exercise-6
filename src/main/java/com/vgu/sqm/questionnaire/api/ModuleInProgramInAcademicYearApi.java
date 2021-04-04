package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.resource.ModuleInProgramInAcademicYear;
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

@WebServlet("/api/moduleInProgramInAcademicYear")
public class ModuleInProgramInAcademicYearApi extends ResourceApi {
    private final static Logger LOGGER =
        Logger.getLogger(ModuleInProgramInAcademicYearApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private static final String p_ModuleID = "mid";
    private static final String p_ProgramID = "pid";
    private static final String p_AYearID = "yid";

    public ModuleInProgramInAcademicYearApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpModuleInProgramInAcademicYear();");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String pId = rs.getString("ProgramID"); // Attribute name: ProgramID
                String mId = rs.getString("ModuleID"); // Attribute name: ModuleID
                int yID = rs.getInt("AYearID"); // Attribute name: AYearID

                resources.add(new ModuleInProgramInAcademicYear(pId, mId, yID));
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
            String ModuleID = json.getJsonString(p_ModuleID).getString();
            String ProgramID = json.getJsonString(p_ProgramID).getString();
            int AYearID = json.getJsonNumber(p_AYearID).intValue();
            if (ModuleInProgramInAcademicYear.checkParametersAreValid(
                    ModuleID, ProgramID, AYearID)) {
                addResourceToDatabase(
                    new ModuleInProgramInAcademicYear(ModuleID, ProgramID, AYearID));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "One or more parameters is invalid: %s, %s, %s".format(p_ModuleID, p_ProgramID, p_AYearID));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Malformed JSON request body");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getParameterMap().containsKey(p_ModuleID)
            && request.getParameterMap().containsKey(p_ProgramID)
            && request.getParameterMap().containsKey(p_AYearID)) {
            try {
                String module = request.getParameter(p_ModuleID);
                String program = request.getParameter(p_ProgramID);
                int year = Integer.parseInt(request.getParameter(p_AYearID));
                deleteResourceFromDataBase(module, program, year);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print("%s must be int".format(p_AYearID));
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(
                "Missing parameters: %s, %s, %s".format(p_ModuleID, p_ProgramID, p_AYearID));
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource) {
        // TODO
    }

    private void deleteResourceFromDataBase(String ModuleID, String ProgramID, int AYearID) {
        // TODO
    }
}
