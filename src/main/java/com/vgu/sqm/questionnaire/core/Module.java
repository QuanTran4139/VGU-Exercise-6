package com.vgu.sqm.questionnaire.api;

import org.json.simple.JSONObject;

public class Module {
    private String ModuleID;
    private String ModuleName;

    public Module(String ModuleID, String ModuleName){
        this.ModuleID = ModuleID;
        this.ModuleName = ModuleName;
    }

    public JSONObject getJsonData(){
        JSONObject module = new JSONObject();
        module.put("ID", this.ModuleID);
        module.put("name", this.ModuleName);
        return module;
    }
}
