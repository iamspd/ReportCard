package com.example.reportcard.model;

public class Subject {
    private String gSubject, gGrade;

    public Subject(String gSubject, String gGrade) {
        this.gSubject = gSubject;
        this.gGrade = gGrade;
    }

    public String getgSubject() {
        return gSubject;
    }

    public String getgGrade() {
        return gGrade;
    }

    public void setgSubject(String gSubject) {
        this.gSubject = gSubject;
    }

    public void setgGrade(String gGrade) {
        this.gGrade = gGrade;
    }
}
