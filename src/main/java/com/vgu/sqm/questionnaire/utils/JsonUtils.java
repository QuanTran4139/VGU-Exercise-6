package com.vgu.sqm.questionnaire.utils;

import com.vgu.sqm.questionnaire.resource.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;

public class JsonUtils {
    public static JsonArray arrayToJson(int[] input) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (int a : input) {
            builder.add(a);
        }
        return builder.build();
    }

    public static JsonArray arrayToJson(int[][] input) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (int[] i : input) {
            JsonArrayBuilder iBuilder = Json.createArrayBuilder();
            for (int j : i) {
                iBuilder.add(j);
            }
            builder.add(iBuilder.build());
        }
        return builder.build();
    }

    public static JsonArray arrayToJson(Resource[] input) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Resource r : input) {
            builder.add(r.exportResourceJson());
        }
        return builder.build();
    }

    public static JsonObject extractJsonRequestBody(HttpServletRequest request) throws IOException {
        String requestBody = request.getReader().lines().collect(Collectors.joining());
        JsonObject obj = Json.createReader(new StringReader(requestBody)).readObject();
        return obj;
    }
}
