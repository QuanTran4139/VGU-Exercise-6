package com.vgu.sqm.questionnaire.resource;

import java.util.regex.Matcher;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Program implements Resource {
    private String ProgramID;
    private String ProgramName;

    public static final String p_ProgramID = "ProgramID";
    public static final String p_ProgramName = "ProgramName";

    public Program(String ProgramID, String ProgramName) {
        this.ProgramID = ProgramID;
        this.ProgramName = ProgramName;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(Program.p_ProgramID, this.ProgramID);
        builder.add(Program.p_ProgramName, this.ProgramName);
        JsonObject obj = builder.build();
        return obj;
    }
    public static boolean checkParametersAreValid(String ProgramID, String ProgramName) {
        return Resource.regex_ID.matcher(ProgramID).matches() && ProgramName.length() > 0
            && ProgramName.length() <= 100 && !ProgramName.isBlank();
    }
}
