package com.vgu.sqm.questionnaire.core;

public class Faculty implements Entity {
    private String FacultyID;
    private String FacultyName;

    public Faculty(String FacultyID, String FacultyName) {
        this.FacultyID = FacultyID;
        this.FacultyName = FacultyName;
    }

    public String getID() {
        return this.FacultyID;
    }

    public String getName() {
        return this.FacultyName;
    }
}
