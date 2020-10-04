package com.example.quedo;

public class Timetable {
    String timetableName, userID;

    public Timetable() {
    }

    public Timetable(String timetableName) {
        this.timetableName = timetableName;
    }

    public Timetable(String timetableName, String userID) {
        this.timetableName = timetableName;
        this.userID = userID;
    }

    public String getTimetableName() {
        return timetableName;
    }

    public void setTimetableName(String timetableName) {
        this.timetableName = timetableName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
