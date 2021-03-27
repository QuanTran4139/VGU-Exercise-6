package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Module;
import java.io.IOException;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/module")
public class ModuleApi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModuleApi() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action =
            request.getParameterMap().containsKey("action") ? request.getParameter("action") : "";
        if (action.equals("dump")) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(dumpModulesJson());
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().print("NOPE");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

    private ArrayList<Module> dumpModuleObjects() {
        ArrayList<Module> modules = new ArrayList<Module>();
        // TODO get modules from DB
        return modules;
    }

    private JsonObject dumpModulesJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        for (Module module : dumpModuleObjects()) {
            builder.add(module.getID(), module.getName());
        }
        JsonObject obj = builder.build();
        return obj;
    }
}
