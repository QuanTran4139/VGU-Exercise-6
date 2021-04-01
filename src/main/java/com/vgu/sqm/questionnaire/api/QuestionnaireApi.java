package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.resource.Questionnaire;
import com.vgu.sqm.questionnaire.resource.Resource;
import com.vgu.sqm.questionnaire.utils.JsonUtils;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/questionnaire")
public class QuestionnaireApi extends ResourceApi {
    private final static Logger LOGGER = Logger.getLogger(QuestionnaireApi.class.getName());
    private static final long serialVersionUID = 1L;

    // parameter names
    private final static String p_LecturerID = "lid";
    private final static String p_ClassID = "cid";
    private final static String p_QuestionnaireID = "qid";
    private final static String p_Gender = "gender";
    private final static String p_Answers = "qa";
    private final static String p_Comment = "comment";

    public QuestionnaireApi() {
        super();
    }

    protected ArrayList<Resource> dumpResource() {
        ArrayList<Resource> resources = new ArrayList<Resource>();

        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL DumpQuestionnaire()");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String lId = rs.getString(1); // Attribute name LecturerID
                String cId = rs.getString(2); // Attribute name ClassID
                String content = rs.getString(3); // Attribute name Content

                if (content == null) {
                    content = "";
                }

                // TODO get actual data for this
                int[] answers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};

                resources.add(new Questionnaire(lId, cId, 0, 'N', answers, content));
            }
            LOGGER.log(Level.INFO, "Getting info from database successfully.");
            db.close();
        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }
        return resources;
    }

    private JsonObject getCounts(String ClassID, String LecturerID, String QuestionnaireID) {
        // TODO replace these placeholder values with data from the DB
        int[][] qa = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9, 0}};
        int countGenderM = 10;
        int countGenderF = 10;
        int countGenderN = 10;

        JsonObjectBuilder objBuilder = Json.createObjectBuilder();
        JsonObject genderCounts = Json.createObjectBuilder()
                                      .add("M", countGenderM)
                                      .add("F", countGenderF)
                                      .add("N", countGenderN)
                                      .build();
        JsonArray qaArray = JsonUtils.arrayToJson(qa);
        objBuilder.add("genders", genderCounts).add("qa", qaArray);
        JsonObject obj = objBuilder.build();
        return obj;
    }

    private float getReponseRate(String ClassID, String LecturerID, String QuestionnaireID) {
        // TODO get the reponse rate (COUNT(questionnaires) / Class.Size)
        return 1;
    }

    @Override
    protected void doGetCustomAction(HttpServletRequest request, HttpServletResponse response,
        String action) throws ServletException, IOException {
        if (action.equals("getCounts")) { // action = getCounts
            if (request.getParameterMap().containsKey("cid")
                && request.getParameterMap().containsKey("lid")
                && request.getParameterMap().containsKey("qid")) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                String cid = request.getParameter("cid");
                String lid = request.getParameter("lid");
                String qid = request.getParameter("qid");
                response.getWriter().print(getCounts(cid, lid, qid));
            } else { // missing parameters
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "The following parameters are required for 'getCounts': cid, lid, qid");
            }
        } else if (action.equals("getResponseRate")) { // action = getReponseRate
            if (request.getParameterMap().containsKey("cid")
                && request.getParameterMap().containsKey("lid")
                && request.getParameterMap().containsKey("qid")) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                String cid = request.getParameter("cid");
                String lid = request.getParameter("lid");
                String qid = request.getParameter("qid");
                response.getWriter().print(getReponseRate(cid, lid, qid));
            } else { // missing parameters
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "The following parameters are required for 'getReponseRate': cid, lid, qid");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String message = String.format("Action '%s' is not supported", action);
            response.getWriter().print(message);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        JsonObject json = JsonUtils.extractJsonRequestBody(request);
        try {
            String LecturerID = json.getJsonString(p_LecturerID).getString();
            String ClassID = json.getJsonString(p_ClassID).getString();
            // Placeholder QID, to be auto-incremented
            int QuestionnaireID = 0;
            // Gender should be a single character
            char gender = json.getJsonString(p_Gender).getChars().charAt(0);
            String comment = json.getJsonString(p_Comment).getString();
            // Get the answers
            JsonArray jsonAnswers = json.getJsonArray(p_Answers);
            int[] answers = {
                jsonAnswers.getInt(0),
                jsonAnswers.getInt(1),
                jsonAnswers.getInt(2),
                jsonAnswers.getInt(3),
                jsonAnswers.getInt(4),
                jsonAnswers.getInt(5),
                jsonAnswers.getInt(6),
                jsonAnswers.getInt(7),
                jsonAnswers.getInt(8),
                jsonAnswers.getInt(9),
                jsonAnswers.getInt(10),
                jsonAnswers.getInt(11),
                jsonAnswers.getInt(12),
                jsonAnswers.getInt(13),
                jsonAnswers.getInt(14),
                jsonAnswers.getInt(15),
                jsonAnswers.getInt(16),
                jsonAnswers.getInt(17),
            };
            if (Questionnaire.checkParametersAreValid(LecturerID, ClassID, gender, answers)) {
                addResourceToDatabase(new Questionnaire(
                    LecturerID, ClassID, QuestionnaireID, gender, answers, comment));
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print("Invalid parameters: %s, %s, %s, %s".format(p_LecturerID, p_ClassID, p_Gender, p_Answers));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Malformed JSON request body");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getParameterMap().containsKey(p_LecturerID)
            && request.getParameterMap().containsKey(p_ClassID)
            && request.getParameterMap().containsKey(p_QuestionnaireID)) {
            try {
                String LecturerID = request.getParameter(p_LecturerID);
                String ClassID = request.getParameter(p_ClassID);
                int QuestionnaireID = Integer.parseInt(request.getParameter(p_QuestionnaireID));
                deleteResourceFromDataBase(LecturerID, ClassID, QuestionnaireID);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print("qid must be int");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Missing parameters: %s, %s, %s".format(
                p_LecturerID, p_ClassID, p_QuestionnaireID));
        }
    }

    @Override
    protected void addResourceToDatabase(Resource resource) {
        // TODO
    }

    private void deleteResourceFromDataBase(
        String LecturerID, String ClassID, int QuestionnaireID) {
        // TODO
    }
}
