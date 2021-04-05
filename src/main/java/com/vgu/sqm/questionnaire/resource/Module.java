package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Module implements Resource {
    private String ModuleID;
    private String ModuleName;

    public static final String p_ModuleID = "ModuleID";
    public static final String p_ModuleName = "ModuleName";

    public Module(String ModuleID, String ModuleName) {
        this.ModuleID = ModuleID;
        this.ModuleName = ModuleName;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(Module.p_ModuleID, this.ModuleID);
        builder.add(Module.p_ModuleName, this.ModuleName);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(String ModuleID, String ModuleName) {
        return ModuleID.length() > 0 && ModuleID.length() <= 10 && !ModuleID.isBlank()
            && ModuleName.length() > 0 && ModuleName.length() <= 100 && !ModuleName.isBlank();
    }
}
