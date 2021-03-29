package com.vgu.sqm.questionnaire.core;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Questionnaire implements Resource {
    private String LecturerID;
    private String ClassID;
    private String Content;

    public Questionnaire(String LecturerID, String ClassID, String Content) {
        this.LecturerID = LecturerID;
        this.ClassID = ClassID;
        this.Content = Content;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("LecturerID", this.LecturerID);
        builder.add("ClassID", this.ClassID);
        builder.add("Content", this.Content);
        JsonObject obj = builder.build();
        return obj;
    }
}
