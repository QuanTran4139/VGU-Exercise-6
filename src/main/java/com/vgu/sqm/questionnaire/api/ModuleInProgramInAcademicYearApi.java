package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Database;
import com.vgu.sqm.questionnaire.core.ModuleInProgramInAcademicYear;
import com.vgu.sqm.questionnaire.core.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/moduleInProgramInAcademicYear")
public class ModuleInProgramInAcademicYearApi extends ResourceApi {
    private final static Logger LOGGER =
        Logger.getLogger(ModuleInProgramInAcademicYearApi.class.getName());
    private static final long serialVersionUID = 1L;

    public ModuleInProgramInAcademicYearApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpModuleInProgramInAcademicYear();");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String pId = rs.getString(1); //Attribute name: ProgramID
                String mId = rs.getString(2); //Attribute name: ModuleID
                int yID = rs.getInt(3); //Attribute name: AYearID

                resources.add(new ModuleInProgramInAcademicYear(pId, mId, yID));
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
