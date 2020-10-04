package com.example.quedo;

public class Subject {
    String subName, subHall, subDate, subTime, subType;

    public Subject() {
    }

    public Subject(String subName, String subHall, String subDate, String subTime, String subType) {
        this.subName = subName;
        this.subHall = subHall;
        this.subDate = subDate;
        this.subTime = subTime;
        this.subType = subType;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubHall() {
        return subHall;
    }

    public void setSubHall(String subHall) {
        this.subHall = subHall;
    }

    public String getSubDate() {
        return subDate;
    }

    public void setSubDate(String subDate) {
        this.subDate = subDate;
    }

    public String getSubTime() {
        return subTime;
    }

    public void setSubTime(String subTime) {
        this.subTime = subTime;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
}
