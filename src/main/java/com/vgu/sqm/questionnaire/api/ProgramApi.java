package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Entity;
import com.vgu.sqm.questionnaire.core.Program;
import com.vgu.sqm.questionnaire.core.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/program")
public class ProgramApi extends EntityApi {
    private final static Logger LOGGER = Logger.getLogger(ProgramApi.class.getName());
    private static final long serialVersionUID = 1L;

    public ProgramApi() {
        super();
    }

    @Override
    protected ArrayList<Entity> dumpEntities() {
        ArrayList<Entity> programs = new ArrayList<Entity>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpProgram()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ProgramId");
                String name = rs.getString("ProgramName");
                programs.add(new Program(id, name));

                LOGGER.log(Level.INFO, "id = " + id);
                LOGGER.log(Level.INFO, "name = " + name);
            }
            LOGGER.log(Level.INFO, "Getting program from database successfully.");
            db.close();
        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }
        return programs;
    }
}
