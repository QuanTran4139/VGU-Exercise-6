package com.vgu.sqm.questionnaire.resource;

import java.util.regex.Matcher;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Class implements Resource {
    private String ClassID;
    private int Size;
    private String SemesterID;
    private String ModuleID;

    public static final String p_ClassID = "ClassID";
    public static final String p_SemesterID = "SemesterID";
    public static final String p_ModuleID = "ModuleID";
    public static final String p_Size = "Size";

    public Class(String ClassID, int Size, String SemesterID, String ModuleID) {
        this.ClassID = ClassID;
        this.Size = Size;
        this.SemesterID = SemesterID;
        this.ModuleID = ModuleID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(Class.p_ClassID, this.ClassID);
        builder.add(Class.p_Size, this.Size);
        builder.add(Class.p_SemesterID, this.SemesterID);
        builder.add(Class.p_ModuleID, this.ModuleID);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(
        String ClassID, int Size, String SemesterID, String ModuleID) {
        return Resource.regex_ID.matcher(ClassID).matches() && SemesterID.length() <= 10
            && ModuleID.length() > 0 && ModuleID.length() <= 10 && !ClassID.isBlank()
            && !SemesterID.isBlank() && !ModuleID.isBlank() && Size > 0;
    }
}
