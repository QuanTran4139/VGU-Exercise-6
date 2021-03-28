package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.AcademicYear;
import com.vgu.sqm.questionnaire.core.Database;
import com.vgu.sqm.questionnaire.core.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/academicYear")
public class AcademicYearApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(AcademicYearApi.class.getName());
    private static final long serialVersionUID = 1L;

    public AcademicYearApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        // TODO dump table
        return resources;
    }
}
