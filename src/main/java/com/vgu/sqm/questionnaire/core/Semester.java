package com.vgu.sqm.questionnaire.core;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Semester implements Resource {
    private String SemesterID;
    private int AYearID;

    public Semester(String SemesterID, int AYearID) {
        this.SemesterID = SemesterID;
        this.AYearID = AYearID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("SemesterID", this.SemesterID);
        builder.add("AYearID", this.AYearID);
        JsonObject obj = builder.build();
        return obj;
    }
}
