package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Semester implements Resource {
    private String SemesterID;
    private int AYearID;

    public static final String p_SemesterID = "SemesterID";
    public static final String p_AYearID = "AYearID";

    public Semester(String SemesterID, int AYearID) {
        this.SemesterID = SemesterID;
        this.AYearID = AYearID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(Semester.p_SemesterID, this.SemesterID);
        builder.add(Semester.p_AYearID, this.AYearID);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(String SemesterID, int AYearID) {
        return SemesterID.length() > 0 && SemesterID.length() <= 10 && !SemesterID.isBlank()
            && AYearID > 0;
    }
}
