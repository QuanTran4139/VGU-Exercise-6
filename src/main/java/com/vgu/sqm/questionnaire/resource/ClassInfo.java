package com.vgu.sqm.questionnaire.resource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class ClassInfo implements Resource {
    private String semesterId;
    private String facultyName;
    private String programName;
    private String lecturerId;
    private String lecturerName;

    public static final String p_SemesterID = "SemesterID";
    public static final String p_FacultyName = "FacultyName";
    public static final String p_ProgramName = "ProgramName";
    public static final String p_LecturerId = "LecturerID";
    public static final String p_LecturerName = "LecturerName";

    public ClassInfo(
            String semesterId, String facultyName, String programName, String lecturerId, String lecturerName) {
        this.semesterId = semesterId;
        this.facultyName = facultyName;
        this.programName = programName;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    public JsonObject exportResourceJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add(ClassInfo.p_SemesterID, this.semesterId);
        builder.add(ClassInfo.p_FacultyName, this.facultyName);
        builder.add(ClassInfo.p_ProgramName, this.programName);
        builder.add(ClassInfo.p_LecturerId, this.lecturerId);
        builder.add(ClassInfo.p_LecturerName, this.lecturerName);
        JsonObject obj = builder.build();
        return obj;
    }

    public static boolean checkParametersAreValid(
            String ClassID, int Size, String SemesterID, String ModuleID) {
        //TODO
        return true;
    }
}
