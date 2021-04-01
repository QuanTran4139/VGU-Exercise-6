package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class ProgramInFacultyInAcademicYear implements Resource {
    private String ProgramID;
    private String FacultyID;
    private int AYearID;

    public ProgramInFacultyInAcademicYear(String ProgramID, String FacultyID, int AYearID) {
        this.ProgramID = ProgramID;
        this.FacultyID = FacultyID;
        this.AYearID = AYearID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("ProgramID", this.ProgramID);
        builder.add("FacultyID", this.FacultyID);
        builder.add("AYearID", this.AYearID);
        JsonObject obj = builder.build();
        return obj;
    }
    public static boolean checkParametersAreValid(String ProgramID, String FacultyID, int AYearID) {
        return ProgramID.length() > 1 && ProgramID.length() <= 10 & !ProgramID.isBlank()
            && FacultyID.length() > 1 && FacultyID.length() <= 10 & !FacultyID.isBlank()
            && AYearID > 0;
    }
}
