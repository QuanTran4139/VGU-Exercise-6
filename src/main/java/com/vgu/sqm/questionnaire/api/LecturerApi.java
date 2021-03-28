package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Entity;
import com.vgu.sqm.questionnaire.core.Lecturer;
import com.vgu.sqm.questionnaire.core.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/lecturer")
public class LecturerApi extends EntityApi {
    private final static Logger LOGGER = Logger.getLogger(LecturerApi.class.getName());
    private static final long serialVersionUID = 1L;

    public LecturerApi() {
        super();
    }

    @Override
    protected ArrayList<Entity> dumpEntities() {
        ArrayList<Entity> lecturers = new ArrayList<Entity>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpLecturer()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("LecturerId");
                String name = rs.getString("LecturerName");
                lecturers.add(new Lecturer(id, name));

                LOGGER.log(Level.INFO, "id = " + id);
                LOGGER.log(Level.INFO, "name = " + name);
            }
            LOGGER.log(Level.INFO, "Getting lecturer from database successfully.");
            db.close();
        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }
        return lecturers;
    }
}
