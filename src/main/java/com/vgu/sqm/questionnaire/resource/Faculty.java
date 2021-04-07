package com.vgu.sqm.questionnaire.resource;

import java.util.regex.Matcher;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Faculty implements Resource {
    private String FacultyID;
    private String FacultyName;

    public static final String p_FacultyID = "FacultyID";
    public static final String p_FacultyName = "FacultyName";

    public Faculty(String FacultyID, String FacultyName) {
        this.FacultyID = FacultyID;
        this.FacultyName = FacultyName;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(Faculty.p_FacultyID, this.FacultyID);
        builder.add(Faculty.p_FacultyName, this.FacultyName);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(String FacultyID, String FacultyName) {
        return Resource.regex_ID.matcher(FacultyID).matches() && FacultyName.length() > 0
            && FacultyName.length() <= 100 && !FacultyName.isBlank();
    }
}
