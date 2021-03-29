package com.vgu.sqm.questionnaire.core;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Faculty implements Resource {
    private String FacultyID;
    private String FacultyName;

    public Faculty(String FacultyID, String FacultyName) {
        this.FacultyID = FacultyID;
        this.FacultyName = FacultyName;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("FacultyID", this.FacultyID);
        builder.add("FacultyName", this.FacultyName);
        JsonObject obj = builder.build();
        return obj;
    }
}
