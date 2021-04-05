package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class AcademicYear implements Resource {
    private int AYearID;
    public static final String p_id = "AYearID";

    public AcademicYear(int AYearID) {
        this.AYearID = AYearID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(AcademicYear.p_id, this.AYearID);
        JsonObject obj = builder.build();
        return obj;
    }
}
