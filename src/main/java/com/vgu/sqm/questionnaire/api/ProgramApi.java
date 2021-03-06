package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.database.SQLCustomException;
import com.vgu.sqm.questionnaire.resource.Program;
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

@WebServlet("/api/program")
public class ProgramApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(ProgramApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private final static String p_ProgramID = "id";
    private final static String p_ProgramName = "name";

    public ProgramApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpProgram()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ProgramId"); // Attribute name ProgramId
                String name = rs.getString("ProgramName"); // Attribute name ProgramName

                resources.add(new Program(id, name));
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
            String id = json.getJsonString(ProgramApi.p_ProgramID).getString();
            String name = json.getJsonString(ProgramApi.p_ProgramName).getString();
            if (Program.checkParametersAreValid(id, name)) {
                addResourceToDatabase(new Program(id, name));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    String.format("One or more parameters is invalid: %s, %s",
                        ProgramApi.p_ProgramID, ProgramApi.p_ProgramName));
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
        if (request.getParameterMap().containsKey(ProgramApi.p_ProgramID)) {
            deleteResourceFromDataBase(request.getParameter(ProgramApi.p_ProgramID));
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
        String pId = entity.getJsonString(Program.p_ProgramID).toString();
        String pName = entity.getJsonString(Program.p_ProgramName).toString();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL AddProgram(?,?,?);");
            st.setString(1, pId);
            st.setString(2, pName);
            st.registerOutParameter(3, Types.INTEGER);

            st.execute();

            int status = st.getInt(3);
            LOGGER.log(Level.INFO, "Status: " + status);
            if (status != 200) {
                throw new SQLCustomException(status);
            }
            db.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE,e.toString());
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE,e.toString());
        }
    }

    private void deleteResourceFromDataBase(String ProgramID) {
        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st =
                    db.prepareCall("CALL DeleteProgram(?,?)");
            st.setString(1, ProgramID);
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
