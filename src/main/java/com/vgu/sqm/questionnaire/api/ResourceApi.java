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
    private final static Logger LOGGER = Logger.getLogger(ResourceApi.class.getName());

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


    protected void printError (int status, String className) {
        StringBuffer sb = new StringBuffer();
        sb.append("Status ");

        switch (status) {
            case 200:
                sb.append(200).append(": No error found");
                break;
            case 401:
                sb.append(401).append(": Wrong academic year id");
                break;
            case 402:
                sb.append(402).append(": Wrong semester id");
                break;
            case 403:
                sb.append(403).append(": Wrong faculty name");
                break;
            case 404:
                sb.append(404).append(": Wrong program name");
                break;
            case 405:
                sb.append(405).append(": Wrong module name");
                break;
            case 406:
                sb.append(406).append(": Wrong lecturer name");
                break;
            case 407:
                sb.append(407).append(": Wrong ClassID");
                break;
            case 408:
                sb.append(408).append(": Wrong Questionnaire id");
                break;
            case 413:
                sb.append(413).append(": Wrong faculty id");
                break;
            case 414:
                sb.append(414).append(": Wrong faculty id");
                break;
            case 415:
                sb.append(415).append(": Wrong module id");
                break;
            case 416:
                sb.append(416).append(": Wrong Lecturer id");
                break;
            case 460:
                sb.append(416).append(": Wrong input academic year id");
                break;
            case 490:
                sb.append(490).append(": duplication error");
                break;
            default:
                sb.append(status).append(": Unknown error");
                break;
        }
        sb.append(" in ").append(className);

        LOGGER.log(Level.INFO, sb.toString());
        //TODO Show error to the user
    }
}
