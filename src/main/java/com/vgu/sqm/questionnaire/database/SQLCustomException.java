package com.vgu.sqm.questionnaire.database;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SQLCustomException extends SQLException {
    public static final Map<Integer, String> errors = new HashMap<Integer, String>();

    static {
        // Declare all the errors here
        errors.put(401, "Wrong Academic Year ID");
        errors.put(402, "Wrong Semester ID");
        errors.put(403, "Wrong Faculty name");
        errors.put(404, "Wrong Program name");
        errors.put(405, "Wrong Module name");
        errors.put(406, "Wrong Lecturer name");
        errors.put(407, "Wrong Class ID");
        errors.put(408, "Wrong Questionnaire ID");
        errors.put(413, "Wrong Faculty ID");
        errors.put(414, "Wrong Program ID");
        errors.put(415, "Wrong Module ID");
        errors.put(416, "Wrong Lecturer ID");
        errors.put(490, "Duplication Error");
    }

    public SQLCustomException(int code) {
        super(constructMessage(code));
    }

    private static String constructMessage(int code) {
        if (errors.containsKey(code)) {
            return errors.get(code);
        } else {
            return String.format("Unknown error code: %s", code);
        }
    }
}
