package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.resource.Lecturer;
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

@WebServlet("/api/lecturer")
public class LecturerApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(LecturerApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private final static String p_LecturerID = "id";
    private final static String p_LecturerName = "name";

    public LecturerApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpLecturer()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1); // Attribute name LectureID
                String name = rs.getString(2); // Attribute name LectureName

                resources.add(new Lecturer(id, name));
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
            String id = json.getJsonString(p_LecturerID).getString();
            String name = json.getJsonString(p_LecturerName).getString();
            if (Lecturer.checkParametersAreValid(id, name)) {
                addResourceToDatabase(new Lecturer(id, name));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "Invalid parameters: %s, %s".format(p_LecturerID, p_LecturerName));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Malformed JSON request body");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getParameterMap().containsKey(p_LecturerID)) {
            deleteResourceFromDataBase(request.getParameter(p_LecturerID));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Missing parameter: %s");
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource) {
        // TODO
    }

    private void deleteResourceFromDataBase(String LectureID) {
        // TODO
    }
}
