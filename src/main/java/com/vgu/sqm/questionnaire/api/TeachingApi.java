package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.resource.Resource;
import com.vgu.sqm.questionnaire.resource.Teaching;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/teaching")
public class TeachingApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(TeachingApi.class.getName());
    private static final long serialVersionUID = 1L;

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
                String lId = rs.getString(1); //Attribute name LectureID
                String cId = rs.getString(2); //Attribute name ClassID

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
}
