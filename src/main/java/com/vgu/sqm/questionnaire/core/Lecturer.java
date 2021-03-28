package com.vgu.sqm.questionnaire.core;

public class Lecturer implements Entity {
    private String LecturerID;
    private String LecturerName;

    public Lecturer(String ModuleID, String ModuleName) {
        this.LecturerID = ModuleID;
        this.LecturerName = ModuleName;
    }

    public String getID() {
        return this.LecturerID;
    }

    public String getName() {
        return this.LecturerName;
    }
}
