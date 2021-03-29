package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class ModuleInProgramInAcademicYear implements Resource {
    private String ModuleID;
    private String ProgramID;
    private int AYearID;

    public ModuleInProgramInAcademicYear(String ProgramID, String ModuleID, int AYearID) {
        this.ModuleID = ModuleID;
        this.ProgramID = ProgramID;
        this.AYearID = AYearID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("ModuleID", this.ModuleID);
        builder.add("ProgramID", this.ProgramID);
        builder.add("AYearID", this.AYearID);
        JsonObject obj = builder.build();
        return obj;
    }
}
