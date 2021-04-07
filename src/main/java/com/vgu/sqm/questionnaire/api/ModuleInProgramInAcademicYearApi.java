package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.database.SQLCustomException;
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
            String ModuleID =
                json.getJsonString(ModuleInProgramInAcademicYearApi.p_ModuleID).getString();
            String ProgramID =
                json.getJsonString(ModuleInProgramInAcademicYearApi.p_ProgramID).getString();
            int AYearID = json.getJsonNumber(ModuleInProgramInAcademicYearApi.p_AYearID).intValue();
            if (ModuleInProgramInAcademicYear.checkParametersAreValid(
                    ModuleID, ProgramID, AYearID)) {
                addResourceToDatabase(
                    new ModuleInProgramInAcademicYear(ModuleID, ProgramID, AYearID));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    String.format("One or more parameters is invalid: %s, %s, %s",
                        ModuleInProgramInAcademicYearApi.p_ModuleID,
                        ModuleInProgramInAcademicYearApi.p_ProgramID,
                        ModuleInProgramInAcademicYearApi.p_AYearID));
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
        if (request.getParameterMap().containsKey(ModuleInProgramInAcademicYearApi.p_ModuleID)
            && request.getParameterMap().containsKey(ModuleInProgramInAcademicYearApi.p_AYearID)) {
            try {
                String module = request.getParameter(ModuleInProgramInAcademicYearApi.p_ModuleID);
                int year = Integer.parseInt(
                    request.getParameter(ModuleInProgramInAcademicYearApi.p_AYearID));
                deleteResourceFromDataBase(module, year);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    String.format("%s must be int", ModuleInProgramInAcademicYearApi.p_AYearID));
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(String.format("Missing parameters: %s, %s",
                ModuleInProgramInAcademicYearApi.p_ModuleID,
                ModuleInProgramInAcademicYearApi.p_AYearID));
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource)
        throws SQLCustomException, NamingException {
        JsonObject entity = resource.exportResourceJson();
        String pId = entity.getJsonString(ModuleInProgramInAcademicYear.p_ProgramID).toString();
        String mId = entity.getJsonString(ModuleInProgramInAcademicYear.p_ModuleID).toString();
        int aYearId = entity.getJsonNumber(ModuleInProgramInAcademicYear.p_AYearID).intValue();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st =
                db.prepareCall("CALL AddModuleInProgramInAcademicYear(?,?,?,?);");
            st.setString(1, pId);
            st.setString(2, mId);
            st.setInt(3, aYearId);
            st.registerOutParameter(4, Types.INTEGER);

            st.execute();

            int status = st.getInt(4);
            LOGGER.log(Level.INFO, "Status: " + status);
            if (status != 200) {
                throw new SQLCustomException(status);
            }
            db.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
    }

    private void deleteResourceFromDataBase(String ModuleID, int AYearID) {
        // TODO
    }
}
