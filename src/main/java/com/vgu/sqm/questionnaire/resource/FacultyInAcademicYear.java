package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class FacultyInAcademicYear implements Resource {
    private String FacultyID;
    private int AYearID;

    public FacultyInAcademicYear(String FacultyID, int AYearID) {
        this.FacultyID = FacultyID;
        this.AYearID = AYearID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("FacultyID", this.FacultyID);
        builder.add("AYearID", this.AYearID);
        JsonObject obj = builder.build();
        return obj;
    }
}
