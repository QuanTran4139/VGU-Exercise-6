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
        // TODO dump resource
        return resources;
    }
}
