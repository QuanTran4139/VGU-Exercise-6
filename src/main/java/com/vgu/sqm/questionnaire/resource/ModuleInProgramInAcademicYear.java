package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class ModuleInProgramInAcademicYear implements Resource {
    private String ModuleID;
    private String ProgramID;
    private int AYearID;

    public static final String p_ModuleID = "ModuleID";
    public static final String p_ProgramID = "ProgramID";
    public static final String p_AYearID = "AYearID";

    public ModuleInProgramInAcademicYear(String ProgramID, String ModuleID, int AYearID) {
        this.ModuleID = ModuleID;
        this.ProgramID = ProgramID;
        this.AYearID = AYearID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(ModuleInProgramInAcademicYear.p_ModuleID, this.ModuleID);
        builder.add(ModuleInProgramInAcademicYear.p_ProgramID, this.ProgramID);
        builder.add(ModuleInProgramInAcademicYear.p_AYearID, this.AYearID);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(String ModuleID, String ProgramID, int AYearID) {
        return ModuleID.length() > 1 && ModuleID.length() <= 10 & !ModuleID.isBlank()
            && ProgramID.length() > 1 && ProgramID.length() <= 10 & !ProgramID.isBlank()
            && AYearID > 0;
    }
}
