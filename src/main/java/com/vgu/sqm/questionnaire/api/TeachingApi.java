package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.resource.Resource;
import com.vgu.sqm.questionnaire.resource.Teaching;
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

@WebServlet("/api/teaching")
public class TeachingApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(TeachingApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private final static String p_LecturerID = "lid";
    private final static String p_ClassID = "cid";

    public TeachingApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpTeaching()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String lId = rs.getString(1); // Attribute name LectureID
                String cId = rs.getString(2); // Attribute name ClassID

                resources.add(new Teaching(lId, cId));
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
            String LectureID = json.getJsonString(p_LecturerID).getString();
            String ClassID = json.getJsonString(p_ClassID).getString();
            if (Teaching.checkParametersAreValid(LectureID, ClassID)) {
                addResourceToDatabase(new Teaching(LectureID, ClassID));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "Invalid parameters: %s, %s".format(p_LecturerID, p_ClassID));
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
            deleteResourceFromDataBase(
                request.getParameter(p_LecturerID), request.getParameter(p_ClassID));
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

    private void deleteResourceFromDataBase(String LectureID, String ClassID) {
        // TODO
    }
}
