package com.vgu.sqm.questionnaire.core;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class AcademicYear implements Resource {
    private int AYearID;

    public AcademicYear(int AYearID) {
        this.AYearID = AYearID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("AYearID", this.AYearID);
        JsonObject obj = builder.build();
        return obj;
    }
}
