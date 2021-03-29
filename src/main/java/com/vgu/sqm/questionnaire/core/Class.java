package com.vgu.sqm.questionnaire.core;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Class implements Resource {
    private String ClassID;
    private int Size;
    private String SemesterID;
    private String ModuleID;

    public Class(String ClassID, int Size, String SemesterID, String ModuleID) {
        this.ClassID = ClassID;
        this.Size = Size;
        this.SemesterID = SemesterID;
        this.ModuleID = ModuleID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("ClassID", this.ClassID);
        builder.add("Size", this.Size);
        builder.add("SemesterID", this.SemesterID);
        builder.add("ModuleID", this.ModuleID);
        JsonObject obj = builder.build();
        return obj;
    }
}
