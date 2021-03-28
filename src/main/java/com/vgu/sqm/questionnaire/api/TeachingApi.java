package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Resource;
import com.vgu.sqm.questionnaire.core.Teaching;
import com.vgu.sqm.questionnaire.core.Database;

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
        // TODO dump resource
        return resources;
    }
}
