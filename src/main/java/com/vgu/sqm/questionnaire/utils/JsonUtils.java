package com.vgu.sqm.questionnaire.utils;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

public class JsonUtils {
    public static JsonArray arrayToJson(int[] input) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (int a : input) {
            builder.add(a);
        }
        return builder.build();
    }
}
