package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Faculty implements Resource {
    private String FacultyID;
    private String FacultyName;

    public Faculty(String FacultyID, String FacultyName) {
        this.FacultyID = FacultyID;
        this.FacultyName = FacultyName;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("FacultyID", this.FacultyID);
        builder.add("FacultyName", this.FacultyName);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(String FacultyID, String FacultyName) {
        return FacultyID.length() > 0 && FacultyID.length() <= 10 && !FacultyID.isBlank()
            && FacultyName.length() > 0 && FacultyName.length() <= 100 && !FacultyName.isBlank();
    }
}
