package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.database.SQLCustomException;
import com.vgu.sqm.questionnaire.resource.Class;
import com.vgu.sqm.questionnaire.resource.Resource;
import com.vgu.sqm.questionnaire.resource.Semester;
import com.vgu.sqm.questionnaire.resource.Faculty;
import com.vgu.sqm.questionnaire.resource.Program;
import com.vgu.sqm.questionnaire.resource.Lecturer;
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


        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL getclassOptions(?,?);");
            st.setString(1, ClassID);
            st.registerOutParameter(2, Types.INTEGER);

            ResultSet rs = st.executeQuery();

            ArrayList<Resource> semesters = new ArrayList<>();
            ArrayList<Resource> faculties = new ArrayList<>();
            ArrayList<Resource> programs = new ArrayList<>();
            ArrayList<Resource> lecturers = new ArrayList<>();

            // get status
            int status = st.getInt(2);
            LOGGER.log(Level.INFO, "status is " + status);
            if (status == 200) {
                while (rs.next()) {
                    semesters.add(new Semester(rs.getString("semesterId"), rs.getInt("AYearId")));
                    faculties.add(new Faculty(rs.getString("FacultyId"), rs.getString("facultyName")));
                    programs.add(new Program(rs.getString("ProgramId"), rs.getString("programName")));
                    lecturers.add(new Lecturer(rs.getString("LecturerId"), rs.getString("lecturerName")));
                }
            } else {
                throw new SQLCustomException(status);
            }
            db.close();
        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }

//        Semester[] semesters = {new Semester("WS2020", 2020), new Semester("SS2021", 2021)};
//        Faculty[] faculties = {new Faculty("A", "41414141"), new Faculty("B", "42424242")};
//        Program[] programs = {new Program("A", "41414141"), new Program("B", "42424242")};
//        Lecturer[] lecturers = {new Lecturer("1", "Bob"), new Lecturer("2", "Alice")};

//        return Json.createObjectBuilder()
//            .add("Semesters", JsonUtils.arrayToJson(semesters))
//            .add("Faculties", JsonUtils.arrayToJson(faculties))
//            .add("Programs", JsonUtils.arrayToJson(programs))
//            .add("Lecturers", JsonUtils.arrayToJson(lecturers))
//            .build();
        //TODO return as ArrayList<Resource>, result is in "classinfos" variable
        return null;
    }

    @Override
    protected void doGetCustomAction(HttpServletRequest request, HttpServletResponse response,
        String action) throws ServletException, IOException {
        if (action.equals("getClassOptions")) {
            if (request.getParameterMap().containsKey(ClassApi.p_ClassID)) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().print(
                    getClassOptions(request.getParameter(ClassApi.p_ClassID)));
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
            String ClassID = json.getJsonString(ClassApi.p_ClassID).getString();
            int Size = json.getJsonNumber(ClassApi.p_Size).intValue();
            String SemesterID = json.getJsonString(ClassApi.p_SemesterID).getString();
            String ModuleID = json.getJsonString(ClassApi.p_ModuleID).getString();
            if (Class.checkParametersAreValid(ClassID, Size, SemesterID, ModuleID)) {
                addResourceToDatabase(new Class(ClassID, Size, SemesterID, ModuleID));
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(String.format(
                    "One or more parameters is invalid: %s, %s, %s, %s", ClassApi.p_ClassID,
                    ClassApi.p_Size, ClassApi.p_SemesterID, ClassApi.p_ModuleID));
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
        if (request.getParameterMap().containsKey(ClassApi.p_ClassID)) {
            deleteResourceFromDataBase(request.getParameter(ClassApi.p_ClassID));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Missing parameter: cid");
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource)
        throws SQLCustomException, NamingException {
        JsonObject entity = resource.exportResourceJson();
        String cId = entity.getJsonString(Class.p_ClassID).toString();
        int size = entity.getJsonNumber(Class.p_Size).intValue();
        String sId = entity.getJsonString(Class.p_SemesterID).toString();
        String mId = entity.getJsonString(Class.p_ModuleID).toString();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL AddClass(?,?,?,?,?);");
            st.setString(1, cId);
            st.setInt(2, size);
            st.setString(3, sId);
            st.setString(4, mId);
            st.registerOutParameter(5, Types.INTEGER);

            st.execute();

            int status = st.getInt(5);
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

    private void deleteResourceFromDataBase(String ClassID) {
        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st =
                    db.prepareCall("CALL DeleteClass(?,?)");
            st.setString(1, ClassID);
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