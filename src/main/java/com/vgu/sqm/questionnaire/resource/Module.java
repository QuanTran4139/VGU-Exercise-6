package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Module implements Resource {
    private String ModuleID;
    private String ModuleName;

    public Module(String ModuleID, String ModuleName) {
        this.ModuleID = ModuleID;
        this.ModuleName = ModuleName;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("ModuleID", this.ModuleID);
        builder.add("ModuleName", this.ModuleName);
        JsonObject obj = builder.build();
        return obj;
    }
}
