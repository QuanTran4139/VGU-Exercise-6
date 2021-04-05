package com.vgu.sqm.questionnaire.exception;

import java.sql.SQLException;

public class SQLCustomException extends SQLException {

    public SQLCustomException(int status, String className) {
        super(constructMessage(status, className));
    }

    private static String constructMessage(int status, String className) {
        StringBuffer sb = new StringBuffer();
        sb.append("Error ");

        switch (status) {
            case 401:
                sb.append(401).append(": Wrong academic year id");
                break;
            case 402:
                sb.append(402).append(": Wrong semester id");
                break;
            case 403:
                sb.append(403).append(": Wrong faculty name");
                break;
            case 404:
                sb.append(404).append(": Wrong program name");
                break;
            case 405:
                sb.append(405).append(": Wrong module name");
                break;
            case 406:
                sb.append(406).append(": Wrong lecturer name");
                break;
            case 407:
                sb.append(407).append(": Wrong ClassID");
                break;
            case 408:
                sb.append(408).append(": Wrong Questionnaire id");
                break;
            case 413:
                sb.append(413).append(": Wrong faculty id");
                break;
            case 414:
                sb.append(414).append(": Wrong faculty id");
                break;
            case 415:
                sb.append(415).append(": Wrong module id");
                break;
            case 416:
                sb.append(416).append(": Wrong Lecturer id");
                break;
            case 460:
                sb.append(416).append(": Wrong input academic year id");
                break;
            case 490:
                sb.append(490).append(": duplication error");
                break;
            default:
                sb.append(status).append(": Unknown error");
                break;
        }
        sb.append(" in ").append(className);

        return sb.toString();
    }
}
