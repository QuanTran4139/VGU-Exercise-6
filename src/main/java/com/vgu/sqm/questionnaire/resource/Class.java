package com.vgu.sqm.questionnaire.resource;

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

    public static boolean checkParametersAreValid(
        String ClassID, int Size, String SemesterID, String ModuleID) {
        return ClassID.length() > 0 && ClassID.length() <= 10 && SemesterID.length() > 0
            && SemesterID.length() <= 10 && ModuleID.length() > 0 && ModuleID.length() <= 10
            && !ClassID.isBlank() && !SemesterID.isBlank() && !ModuleID.isBlank() && Size > 0;
    }
}
