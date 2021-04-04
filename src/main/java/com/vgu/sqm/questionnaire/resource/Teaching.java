package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Teaching implements Resource {
    private String LecturerID;
    private String ClassID;

    public Teaching(String LecturerID, String ClassID) {
        this.LecturerID = LecturerID;
        this.ClassID = ClassID;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("LecturerID", this.LecturerID);
        builder.add("ClassID", this.ClassID);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(String LecturerID, String ClassID) {
        return LecturerID.length() > 0 && LecturerID.length() <= 10 && !LecturerID.isBlank()
            && LecturerID.length() > 0 && LecturerID.length() <= 10 && !LecturerID.isBlank();
    }
}
