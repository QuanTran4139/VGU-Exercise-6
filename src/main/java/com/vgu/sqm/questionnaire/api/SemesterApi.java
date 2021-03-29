package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Database;
import com.vgu.sqm.questionnaire.core.Resource;
import com.vgu.sqm.questionnaire.core.Semester;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/semester")
public class SemesterApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(SemesterApi.class.getName());
    private static final long serialVersionUID = 1L;

    public SemesterApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpSemester();");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String sId = rs.getString(1); //Attribute name: SemesterID
                int aId = rs.getInt(2); //Attribute name: AYearID

                resources.add(new Semester(sId, aId));
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
