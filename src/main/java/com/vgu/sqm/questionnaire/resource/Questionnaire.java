package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import com.vgu.sqm.questionnaire.utils.JsonUtils;

public class Questionnaire implements Resource {
    private String LecturerID;
    private String ClassID;
    private int QuestionnaireID;
    private char gender;
    private int[] answers;
    private String comment;

    public Questionnaire(
        String LecturerID, String ClassID, int QuestionnaireID, char gender, int[] answers, String comment) {
        this.LecturerID = LecturerID;
        this.ClassID = ClassID;
        this.QuestionnaireID = QuestionnaireID;
        this.gender = gender;
        this.answers = answers;
        this.comment = comment;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("LecturerID", this.LecturerID);
        builder.add("ClassID", this.ClassID);
        builder.add("QuestionnaireID", this.QuestionnaireID);
        builder.add("gender", this.gender);
        builder.add("answers", JsonUtils.arrayToJson(this.answers));
        builder.add("comment", this.comment);
        JsonObject obj = builder.build();
        return obj;
    }
}
