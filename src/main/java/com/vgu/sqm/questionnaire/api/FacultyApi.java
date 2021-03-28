package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Entity;
import com.vgu.sqm.questionnaire.core.Faculty;
import com.vgu.sqm.questionnaire.core.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/faculty")
public class FacultyApi extends EntityApi {
    private final static Logger LOGGER = Logger.getLogger(FacultyApi.class.getName());
    private static final long serialVersionUID = 1L;

    public FacultyApi() {
        super();
    }

    @Override
    protected ArrayList<Entity> dumpEntities() {
        ArrayList<Entity> faculties = new ArrayList<Entity>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpFaculty()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("FacultyId");
                String name = rs.getString("FacultyName");
                faculties.add(new Faculty(id, name));

                LOGGER.log(Level.INFO, "id = " + id);
                LOGGER.log(Level.INFO, "name = " + name);
            }
            LOGGER.log(Level.INFO, "Getting faculty from database successfully.");
            db.close();
        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }
        return faculties;
    }
}
