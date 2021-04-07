package com.vgu.sqm.questionnaire.resource;

import java.util.regex.Pattern;
import javax.json.JsonObject;

public interface Resource {
    static final Pattern regex_ID = Pattern.compile("[A-Za-z0-9]{1,10}");

    public JsonObject exportResourceJson();
}
