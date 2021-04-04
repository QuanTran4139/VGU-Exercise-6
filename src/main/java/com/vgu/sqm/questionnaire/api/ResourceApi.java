package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.resource.Resource;
import com.vgu.sqm.questionnaire.utils.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ResourceApi extends HttpServlet {
    abstract protected void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;
    abstract protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;
    abstract protected void addResourceToDatabase(Resource resource);
    abstract ArrayList<Resource> dumpResource();

    protected JsonArray ResourceToJson(ArrayList<Resource> resources) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Resource resource : resources) {
            builder.add(resource.exportResourceJson());
        }
        JsonArray array = builder.build();
        return array;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action =
            request.getParameterMap().containsKey("action") ? request.getParameter("action") : "";
        if (action.equals("dump")) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(ResourceToJson(dumpResource()));
        } else if (action.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("You need to supply an 'action'");
        } else {
            doGetCustomAction(request, response, action);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGetCustomAction(HttpServletRequest request, HttpServletResponse response,
        String action) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        String message = String.format("Action '%s' is not supported", action);
        response.getWriter().print(message);
    }
}
