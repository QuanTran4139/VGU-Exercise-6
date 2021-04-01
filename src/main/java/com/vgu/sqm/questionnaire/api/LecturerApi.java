package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.resource.Lecturer;
import com.vgu.sqm.questionnaire.resource.Resource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/lecturer")
public class LecturerApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(LecturerApi.class.getName());
    private static final long serialVersionUID = 1L;

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
        // TODO
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // TODO
    }

    @Override
    protected void addResourceToDatabase(Resource resource) {
        // TODO
    }

    private void deleteResourceFromDataBase(int AYearID) {
        // TODO
    }
}
