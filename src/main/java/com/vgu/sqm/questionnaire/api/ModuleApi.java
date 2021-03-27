package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Module;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

@WebServlet("/api/module")
public class ModuleApi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModuleApi() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getParameter("action").equals("dump")) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(dumpModulesJson());
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

    private JSONObject dumpModulesJson(){
        JSONObject output = new JSONObject();
        for (Module module : dumpModuleObjects()) {
            output.put(module.getID(), module.getName());
        }
        return output;
    }
}
