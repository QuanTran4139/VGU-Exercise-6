package com.vgu.sqm.questionnaire.resource;

import java.util.regex.Matcher;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class ProgramInFacultyInAcademicYear implements Resource {
    private String ProgramID;
    private String FacultyID;
    private int AYearID;

    public static final String p_ProgramID = "ProgramID";
    public static final String p_FacultyID = "FacultyID";
    public static final String p_AYearID = "AYearID";

    public ProgramInFacultyInAcademicYear(String ProgramID, String FacultyID, int AYearID) {
        this.ProgramID = ProgramID;
        this.FacultyID = FacultyID;
        this.AYearID = AYearID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(ProgramInFacultyInAcademicYear.p_ProgramID, this.ProgramID);
        builder.add(ProgramInFacultyInAcademicYear.p_FacultyID, this.FacultyID);
        builder.add(ProgramInFacultyInAcademicYear.p_AYearID, this.AYearID);
        JsonObject obj = builder.build();
        return obj;
    }
    public static boolean checkParametersAreValid(String ProgramID, String FacultyID, int AYearID) {
        return Resource.regex_ID.matcher(ProgramID).matches()
            && Resource.regex_ID.matcher(FacultyID).matches() && AYearID > 0;
    }
}
