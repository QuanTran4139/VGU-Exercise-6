package com.vgu.sqm.questionnaire.resource;

import java.util.regex.Matcher;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Lecturer implements Resource {
    private String LecturerID;
    private String LecturerName;

    public static final String p_LecturerID = "LecturerID";
    public static final String p_LecturerName = "LecturerName";

    public Lecturer(String LecturerID, String LecturerName) {
        this.LecturerID = LecturerID;
        this.LecturerName = LecturerName;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(Lecturer.p_LecturerID, this.LecturerID);
        builder.add(Lecturer.p_LecturerName, this.LecturerName);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(String LecturerID, String LecturerName) {
        return Resource.regex_ID.matcher(LecturerID).matches() && LecturerName.length() > 0
            && LecturerName.length() <= 100 && !LecturerName.isBlank();
    }
}
