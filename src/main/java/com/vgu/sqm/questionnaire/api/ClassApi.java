package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.resource.Class;
import com.vgu.sqm.questionnaire.resource.Faculty;
import com.vgu.sqm.questionnaire.resource.Lecturer;
import com.vgu.sqm.questionnaire.resource.Program;
import com.vgu.sqm.questionnaire.resource.Resource;
import com.vgu.sqm.questionnaire.resource.Semester;
import com.vgu.sqm.questionnaire.utils.JsonUtils;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/class")
public class ClassApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(ClassApi.class.getName());
    private static final long serialVersionUID = 1L;

    public ClassApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpClass();");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String cId = rs.getString(1); // Attribute name: ClassID
                int size = rs.getInt(2); // Attribute name: Size
                String sId = rs.getString(3); // Attribute name: SemesterID
                String mId = rs.getString(4); // Attribute name: ModuleID

                resources.add(new Class(cId, size, sId, mId));
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

    private JsonObject getClassOptions(String ClassID) {
        // TODO replace the following sample data with real data from the DB
        Semester[] semesters = {new Semester("WS2020", 2020), new Semester("SS2021", 2021)};
        Faculty[] faculties = {new Faculty("A", "41414141"), new Faculty("B", "42424242")};
        Program[] programs = {new Program("A", "41414141"), new Program("B", "42424242")};
        Lecturer[] lecturers = {new Lecturer("1", "Bob"), new Lecturer("2", "Alice")};

        return Json.createObjectBuilder()
            .add("Semesters", JsonUtils.arrayToJson(semesters))
            .add("Faculties", JsonUtils.arrayToJson(faculties))
            .add("Programs", JsonUtils.arrayToJson(programs))
            .add("Lecturers", JsonUtils.arrayToJson(lecturers))
            .build();
    }

    @Override
    protected void doGetCustomAction(HttpServletRequest request, HttpServletResponse response,
        String action) throws ServletException, IOException {
        if (action.equals("getClassOptions")) {
            if (request.getParameterMap().containsKey("cid")) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().print(getClassOptions(request.getParameter("cid")));
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "The following parameter is required for 'getClassOptions': cid");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String message = String.format("Action '%s' is not supported", action);
            response.getWriter().print(message);
        }
    }
}
