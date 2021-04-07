package com.vgu.sqm.questionnaire.resource;

import java.util.regex.Matcher;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Teaching implements Resource {
    private String LecturerID;
    private String ClassID;

    public static final String p_LecturerID = "LecturerID";
    public static final String p_ClassID = "ClassID";

    public Teaching(String LecturerID, String ClassID) {
        this.LecturerID = LecturerID;
        this.ClassID = ClassID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(Teaching.p_LecturerID, this.LecturerID);
        builder.add(Teaching.p_ClassID, this.ClassID);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(String LecturerID, String ClassID) {
        return Resource.regex_ID.matcher(LecturerID).matches()
            && Resource.regex_ID.matcher(ClassID).matches();
    }
}
