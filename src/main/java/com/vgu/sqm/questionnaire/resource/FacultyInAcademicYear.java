package com.vgu.sqm.questionnaire.resource;

import java.util.regex.Matcher;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class FacultyInAcademicYear implements Resource {
    private String FacultyID;
    private int AYearID;

    public static final String p_FacultyID = "FacultyID";
    public static final String p_AYearID = "AYearID";

    public FacultyInAcademicYear(String FacultyID, int AYearID) {
        this.FacultyID = FacultyID;
        this.AYearID = AYearID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(FacultyInAcademicYear.p_FacultyID, this.FacultyID);
        builder.add(FacultyInAcademicYear.p_AYearID, this.AYearID);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(String FacultyID, int AYearID) {
        return Resource.regex_ID.matcher(FacultyID).matches() && AYearID > 0;
    }
}
