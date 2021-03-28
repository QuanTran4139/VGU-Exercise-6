package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Database;
import com.vgu.sqm.questionnaire.core.Module;
import com.vgu.sqm.questionnaire.core.Resource;
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
        ArrayList<Resource> modules = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpModule()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ModuleId");
                String name = rs.getString("ModuleName");
                modules.add(new Module(id, name));

                LOGGER.log(Level.INFO, "id = " + id);
                LOGGER.log(Level.INFO, "name = " + name);
            }
            LOGGER.log(Level.INFO, "Getting module from database successfully.");
            db.close();
        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }
        return modules;
    }
}
