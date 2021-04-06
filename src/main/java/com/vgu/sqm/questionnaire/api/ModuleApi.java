package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.database.SQLCustomException;
import com.vgu.sqm.questionnaire.resource.Module;
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

@WebServlet("/api/module")
public class ModuleApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(ModuleApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private final static String p_ModuleID = "id";
    private final static String p_ModuleName = "name";

    public ModuleApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpModule()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ModuleId"); // Attribute name ModuleId
                String name = rs.getString("ModuleName"); // Attribute name ModuleName

                resources.add(new Module(id, name));
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
            String id = json.getJsonString(ModuleApi.p_ModuleID).getString();
            String name = json.getJsonString(ModuleApi.p_ModuleName).getString();
            if (Module.checkParametersAreValid(id, name)) {
                addResourceToDatabase(new Module(id, name));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    String.format("One or more parameters is invalid: %s, %s", ModuleApi.p_ModuleID,
                        ModuleApi.p_ModuleName));
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
        if (request.getParameterMap().containsKey(ModuleApi.p_ModuleID)) {
            deleteResourceFromDataBase(request.getParameter(ModuleApi.p_ModuleID));
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
        String mId = entity.getJsonString(Module.p_ModuleID).toString();
        String mName = entity.getJsonString(Module.p_ModuleName).toString();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL AddModule(?,?,?);");
            st.setString(1, mId);
            st.setString(2, mName);
            st.registerOutParameter(3, Types.INTEGER);

            st.execute();

            int status = st.getInt(3);
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

    private void deleteResourceFromDataBase(String ModuleID) {
        // TODO
    }
}
