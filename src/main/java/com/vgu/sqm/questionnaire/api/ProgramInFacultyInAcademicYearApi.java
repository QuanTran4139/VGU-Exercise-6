package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Resource;
import com.vgu.sqm.questionnaire.core.ProgramInFacultyInAcademicYear;
import com.vgu.sqm.questionnaire.core.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/programInFacultyInAcademicYearApi")
public class ProgramInFacultyInAcademicYearApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(ProgramInFacultyInAcademicYearApi.class.getName());
    private static final long serialVersionUID = 1L;

    public ProgramInFacultyInAcademicYearApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        // TODO dump resource
        return resources;
    }
}
