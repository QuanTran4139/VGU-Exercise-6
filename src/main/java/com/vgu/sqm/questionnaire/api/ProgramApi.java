package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Database;
import com.vgu.sqm.questionnaire.core.Program;
import com.vgu.sqm.questionnaire.core.Resource;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/program")
public class ProgramApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(ProgramApi.class.getName());
    private static final long serialVersionUID = 1L;

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
                String id = rs.getString(1); //Attribute name ProgramId
                String name = rs.getString(2); //Attribute name ProgramName

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
}
