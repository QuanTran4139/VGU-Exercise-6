package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Lecturer implements Resource {
    private String LecturerID;
    private String LecturerName;

    public Lecturer(String LecturerID, String LecturerName) {
        this.LecturerID = LecturerID;
        this.LecturerName = LecturerName;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("LecturerID", this.LecturerID);
        builder.add("LecturerName", this.LecturerName);
        JsonObject obj = builder.build();
        return obj;
    }
}
