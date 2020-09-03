package com.example.reportcard.model;

import io.realm.RealmObject;

public class Student extends RealmObject {
    private String sFirstName, sLastName, sEmail, sPhone,
            sUsername, sPassword;

    public String getsFirstName() {
        return sFirstName;
    }

    public String getsLastName() {
        return sLastName;
    }

    public String getsEmail() {
        return sEmail;
    }

    public String getsPhone() {
        return sPhone;
    }

    public String getsUsername() {
        return sUsername;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsFirstName(String sFirstName) {
        this.sFirstName = sFirstName;
    }

    public void setsLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public void setsUsername(String sUsername) {
        this.sUsername = sUsername;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }
}
