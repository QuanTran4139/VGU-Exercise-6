package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Class;
import com.vgu.sqm.questionnaire.core.Database;
import com.vgu.sqm.questionnaire.core.Resource;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

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
                String cId = rs.getString(1); //Attribute name: ClassID
                int size = rs.getInt(2); //Attribute name: Size
                String sId = rs.getString(3); //Attribute name: SemesterID
                String mId = rs.getString(4); //Attribute name: ModuleID

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
}
