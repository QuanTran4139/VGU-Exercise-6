package com.vgu.sqm.questionnaire.api;

import com.vgu.sqm.questionnaire.database.Database;
import com.vgu.sqm.questionnaire.resource.Questionnaire;
import com.vgu.sqm.questionnaire.resource.Resource;
import com.vgu.sqm.questionnaire.utils.JsonUtils;
import com.vgu.sqm.questionnaire.exception.SQLCustomException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
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
    private final static long serialVersionUID = 1L;

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
                int qId = rs.getInt("QuestionnaireId"); // Attribute name QuestionnaireId
                String lId = rs.getString("LecturerId"); // Attribute name LecturerId
                String cId = rs.getString("ClassId"); // Attribute name ClassId
                char gender = rs.getString("Gender").charAt(0); // Attribute name Gender

                // TODO need to fixed type question[0]
                int[] answers = new int[18];

                answers[0] = 0;

                for (int i = 1; i < answers.length; i++) {
                    answers[i] = rs.getInt("Question" + i);
                }

                String content = rs.getString("Comment");
                if (content == null) {
                    content = "";
                }

                resources.add(new Questionnaire(lId, cId, qId, gender, answers, content));
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

    private JsonObject getCounts(String ClassID, String LecturerID) {
        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL GetGenderCountByID(?,?,?)");
            st.setString(1, ClassID);
            st.setString(2, LecturerID);
            st.registerOutParameter(3, Types.INTEGER);

            ResultSet rs = st.executeQuery();
            HashMap<String, Integer> countsByGender = new HashMap<>();

            // get status
            int status = st.getInt(3);
            LOGGER.log(Level.INFO, "status (count gender) is " + status);
            if (status == 200) {
                while (rs.next()) {
                    String gender = rs.getString("Gender");
                    int count = rs.getInt("Count");

                    countsByGender.put(gender, count);
                }
            } else {
                throw new SQLCustomException(status,this.getClass().getName());
            }

            int[][] qa = new int[18][6];

            //TODO need to change Question0 after
            for (int i =0 ; i < 6; i++) {
                qa[0][i] = 0;
            }
            for (int i = 1; i < 18; i++) {
                st = db.prepareCall("CALL GetResponseByQuestion(?,?,?,?)");
                st.setString(1, ClassID);
                st.setString(2, LecturerID);
                st.setInt(3, i);
                st.registerOutParameter(4, Types.INTEGER);

                rs = st.executeQuery();

                // get status
                status = st.getInt(4);
                LOGGER.log(Level.INFO, "status (get response) is " + status);
                if (status == 200) {
                    while (rs.next()) {
                        for (int j = 0; j <= 5; j++) {
                            qa[i][j] = rs.getInt(j + 4);
                        }
                    }
                } else {
                    throw new SQLCustomException(status,this.getClass().getName());
                }
            }
            db.close();

            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            JsonObject genderCounts =
                Json.createObjectBuilder()
                    .add("M", countsByGender.get("M") == null ? -1 : countsByGender.get("M"))
                    .add("F", countsByGender.get("F") == null ? -1 : countsByGender.get("F"))
                    .add("N", countsByGender.get("N") == null ? -1 : countsByGender.get("N"))
                    .build();
            JsonArray qaArray = JsonUtils.arrayToJson(qa);
            objBuilder.add("genders", genderCounts).add("qa", qaArray);
            JsonObject obj = objBuilder.build();

            return obj;
        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }
        return null;
    }

    private float getReponseRate(String ClassID, String LecturerID) {
        float result = -1.0f;
        try {
            Connection db = Database.getAcademiaConnection();
            CallableStatement st = db.prepareCall("CALL GetResponseRate(?,?,?)");
            st.setString(1, LecturerID);
            st.setString(2, ClassID);
            st.registerOutParameter(3, Types.INTEGER);

            ResultSet rs = st.executeQuery();

            // status
            int status = st.getInt(3);
            LOGGER.log(Level.INFO, "status is " + status);

            if (status == 200 && rs.next()) {
                result = rs.getInt("Response_Rate");
            } else {
                throw new SQLCustomException(status,this.getClass().getName());
            }

            db.close();
        } catch (SQLException e1) {
            LOGGER.log(Level.SEVERE, e1.toString());
        } catch (NamingException e2) {
            LOGGER.log(Level.SEVERE, e2.toString());
        }
        return result;
    }

    @Override
    protected void doGetCustomAction(HttpServletRequest request, HttpServletResponse response,
        String action) throws ServletException, IOException {
        if (action.equals("getCounts")) { // action = getCounts
            if (request.getParameterMap().containsKey("cid")
                && request.getParameterMap().containsKey("lid")) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                String cid = request.getParameter("cid");
                String lid = request.getParameter("lid");
                response.getWriter().print(getCounts(cid, lid));
            } else { // missing parameters
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "The following parameters are required for 'getCounts': cid, lid");
            }
        } else if (action.equals("getResponseRate")) { // action = getReponseRate
            if (request.getParameterMap().containsKey("cid")
                && request.getParameterMap().containsKey("lid")) {
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                String cid = request.getParameter("cid");
                String lid = request.getParameter("lid");
                response.getWriter().print(getReponseRate(cid, lid));
            } else { // missing parameters
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "The following parameters are required for 'getReponseRate': cid, lid");
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
            if (Questionnaire.checkParametersAreValid(
                    LecturerID, ClassID, gender, answers, comment)) {
                addResourceToDatabase(new Questionnaire(
                    LecturerID, ClassID, QuestionnaireID, gender, answers, comment));
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(
                    "One or more parameters is invalid: %s, %s, %s, %s".format(
                        p_LecturerID, p_ClassID, p_Gender, p_Answers));
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
