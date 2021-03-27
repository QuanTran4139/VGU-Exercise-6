package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.core.Entity;
import java.io.IOException;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class EntityApi extends HttpServlet {
    abstract ArrayList<Entity> dumpEntities();

    JsonObject EntitiesToJson(ArrayList<Entity> entities) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        for (Entity entity : entities) {
            builder.add(entity.getID(), entity.getName());
        }
        JsonObject obj = builder.build();
        return obj;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action =
            request.getParameterMap().containsKey("action") ? request.getParameter("action") : "";
        if (action.equals("dump")) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(EntitiesToJson(dumpEntities()));
        } else if (action.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("You need to supply an 'action'");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String message = String.format("Action '%s' is not supported", action);
            response.getWriter().print(message);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }
}
