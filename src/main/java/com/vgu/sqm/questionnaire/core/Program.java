package com.vgu.sqm.questionnaire.core;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Program implements Resource {
    private String ProgramID;
    private String ProgramName;

    public Program(String ProgramID, String ProgramName) {
        this.ProgramID = ProgramID;
        this.ProgramName = ProgramName;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("ProgramID", this.ProgramID);
        builder.add("ProgramName", this.ProgramName);
        JsonObject obj = builder.build();
        return obj;
    }
}
