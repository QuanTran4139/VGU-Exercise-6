package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.resource.Module;
import com.vgu.sqm.questionnaire.resource.Resource;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/module")
public class ModuleApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(ModuleApi.class.getName());
    private static final long serialVersionUID = 1L;

    public ModuleApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpModule()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1); //Attribute name ModuleId
                String name = rs.getString(2); //Attribute name ModuleName

                resources.add(new Module(id, name));
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
