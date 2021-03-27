package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Entity;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/module")
public class ModuleApi extends EntityApi {
    private static final long serialVersionUID = 1L;

    public ModuleApi() {
        super();
    }

    protected ArrayList<Entity> dumpEntities() {
        ArrayList<Entity> modules = new ArrayList<Entity>();
        // TODO get modules from DB
        return modules;
    }
}
