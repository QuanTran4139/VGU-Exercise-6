package com.vgu.sqm.questionnaire.resource;

import com.vgu.sqm.questionnaire.utils.JsonUtils;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Questionnaire implements Resource {
    private String LecturerID;
    private String ClassID;
    private int QuestionnaireID;
    private char gender;
    private int[] answers;
    private String comment;

    public static final String p_LecturerID = "LecturerID";
    public static final String p_ClassID = "ClassID";
    public static final String p_QuestionnaireID = "QuestionnaireID";
    public static final String p_gender = "gender";
    public static final String p_answers = "answers";
    public static final String p_comment = "comment";

    public Questionnaire(String LecturerID, String ClassID, int QuestionnaireID, char gender,
        int[] answers, String comment) {
        this.LecturerID = LecturerID;
        this.ClassID = ClassID;
        this.QuestionnaireID = QuestionnaireID;
        this.gender = gender;
        this.answers = answers;
        this.comment = comment;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(Questionnaire.p_LecturerID, this.LecturerID);
        builder.add(Questionnaire.p_ClassID, this.ClassID);
        builder.add(Questionnaire.p_QuestionnaireID, this.QuestionnaireID);
        builder.add(Questionnaire.p_gender, this.gender);
        builder.add(Questionnaire.p_answers, JsonUtils.arrayToJson(this.answers));
        builder.add(Questionnaire.p_LecturerID, this.comment);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(
        String LecturerID, String ClassID, char gender, int[] answers, String comment) {
        return
            // LecturerID
            LecturerID.length() > 0 && LecturerID.length() <= 10
            && !LecturerID.isBlank()
            // ClassID
            && ClassID.length() > 0 && ClassID.length() <= 10
            && !ClassID.isBlank()
            // gender
            && (gender == 'M' || gender == 'F' || gender == 'N')
            // questionnaire answers (0 == N/A)
            && answers[0]  >= 0 && answers[0]  <= 5
            && answers[1]  >= 0 && answers[1]  <= 5
            && answers[2]  >= 0 && answers[2]  <= 5
            && answers[3]  >= 0 && answers[3]  <= 5
            && answers[4]  >= 0 && answers[4]  <= 5
            && answers[5]  >  0 && answers[5]  <= 5
            && answers[6]  >  0 && answers[6]  <= 5
            && answers[7]  >  0 && answers[7]  <= 5
            && answers[8]  >= 0 && answers[8]  <= 5
            && answers[9]  >= 0 && answers[9]  <= 5
            && answers[10] >= 0 && answers[10] <= 5
            && answers[11] >= 0 && answers[11] <= 5
            && answers[12] >= 0 && answers[12] <= 5
            && answers[13] >= 0 && answers[13] <= 5
            && answers[14] >= 0 && answers[14] <= 5
            && answers[15] >= 0 && answers[15] <= 5
            && answers[16] >= 0 && answers[16] <= 5
            && answers[17] >= 0 && answers[17] <= 5
            // Comment max character count = 500
            && comment.length() <= 500;
    }
}
