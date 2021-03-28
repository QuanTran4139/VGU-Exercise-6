package com.vgu.sqm.questionnaire.core;

public class Program implements Entity {
    private String ProgramID;
    private String ProgramName;

    public Program(String ProgramID, String ProgramName) {
        this.ProgramID = ProgramID;
        this.ProgramName = ProgramName;
    }

    public String getID() {
        return this.ProgramID;
    }

    public String getName() {
        return this.ProgramName;
    }
}
