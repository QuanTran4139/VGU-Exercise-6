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

    // parameter names
    private final static String p_ClassID = "cid";
    private final static String p_Size = "size";
    private final static String p_SemesterID = "sid";
    private final static String p_ModuleID = "mid";

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
                String cId = rs.getString("ClassID"); // Attribute name: ClassID
                int size = rs.getInt("Size"); // Attribute name: Size
                String sId = rs.getString("SemesterID"); // Attribute name: SemesterID
                String mId = rs.getString("ModuleID"); // Attribute name: ModuleID

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
        // DONE: get semesterId, facultyName, programName, lecturerName

        ArrayList<ClassInfo> classInfos = new ArrayList<>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL getclassOptions(?,?);");
            st.setString(1, ClassID);
            st.registerOutParameter(2, Types.INTEGER);

            ResultSet rs = st.executeQuery();


            while (rs.next()) {
                String sID = rs.getString(1); // Attribute name: semesterId
                String fName = rs.getString(2); // Attribute name: facultyName
                String pName = rs.getString(3); // Attribute name: programName
                String lName = rs.getString(4); // Attribute name: lecturerName
                LOGGER.log(Level.INFO, "sId = " + sID);
                LOGGER.log(Level.INFO, "fName = " + fName);
                LOGGER.log(Level.INFO, "pName = " + pName);
                LOGGER.log(Level.INFO, "lName = " + lName);

                classInfos.add(new ClassInfo(sID, fName, pName, lName));
            }
            //get status
            int status = st.getInt(2);
            LOGGER.log(Level.INFO, "status is " + status);

            LOGGER.log(Level.INFO, "Getting info from database successfully.");
            db.close();
        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }


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
            if (request.getParameterMap().containsKey(p_ClassID)) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().print(getClassOptions(request.getParameter(p_ClassID)));
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

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO
        try {
            JsonObject json = JsonUtils.extractJsonRequestBody(request);
            String ClassID = json.getJsonString(p_ClassID).getString();
            int Size = json.getJsonNumber(p_Size).intValue();
            String SemesterID = json.getJsonString(p_SemesterID).getString();
            String ModuleID = json.getJsonString(p_ModuleID).getString();
            if (Class.checkParametersAreValid(ClassID, Size, SemesterID, ModuleID)) {
                addResourceToDatabase(new Class(ClassID, Size, SemesterID, ModuleID));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                        "One or more parameters is invalid: %s, %s, %s, %s".format(
                                p_ClassID, p_Size, p_SemesterID, p_ModuleID));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Malformed JSON request body");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameterMap().containsKey(p_ClassID)) {
            deleteResourceFromDataBase(request.getParameter(p_ClassID));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Missing parameter: cid");
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource) {
        // TODO
    }

    private void deleteResourceFromDataBase(String ClassID) {
        // TODO
    }
}

class ClassInfo {
    private String semesterId;
    private String facultyName;
    private String programName;
    private String lecturerName;

    public ClassInfo(String semesterId, String facultyName, String programName, String lecturerName) {
        this.semesterId = semesterId;
        this.facultyName = facultyName;
        this.programName = programName;
        this.lecturerName = lecturerName;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String getProgramName() {
        return programName;
    }

    public String getLecturerName() {
        return lecturerName;
    }
}
