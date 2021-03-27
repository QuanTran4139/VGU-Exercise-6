package com.vgu.sqm.questionnaire.core;

public class Module {
    private String ModuleID;
    private String ModuleName;

    public Module(String ModuleID, String ModuleName) {
        this.ModuleID = ModuleID;
        this.ModuleName = ModuleName;
    }

    public String getID() {
        return this.ModuleID;
    }

    public String getName() {
        return this.ModuleName;
    }
}
