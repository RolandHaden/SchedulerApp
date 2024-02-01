package com.example.schedulerapp.ui.profile;

import java.util.UUID;

//This is for objects that will be displayed through the calendar
public class classObject {
    private String courseName;
    private String startTime;
    private String endTime;
    private String professorName;
    private String classDays;
    private final UUID id;

    public classObject(String courseName, String startTime, String endTime, String classDays, String professorName, UUID id) {
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classDays = classDays;
        this.professorName = professorName;
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getClassDays() {
        return classDays;
    }

    public void setClassDays(String classDays) {
        this.classDays = classDays;
    }


    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public UUID getId() {
        return id;
    }
}
