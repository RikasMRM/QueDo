package com.example.quedo;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Document {
    public String docType;
    public String docuID;
    public String docuName;
    public String docuAuth;
    public String childRes;
    public String docuLink;
    public String userID;

    public Document() {
    }


    public Document(String docuName, String docuAuth, String docType) {
        this.docuName = docuName;
        this.docuAuth = docuAuth;
        this.docType = docType;
    }

    public Document(String docuName, String docuAuth, String docType, String childRes, String userID, String docuLink) {
        this.docuName = docuName;
        this.docuAuth = docuAuth;
        this.docType = docType;
        this.childRes = childRes;
        this.userID = userID;
        this.docuLink = docuLink;
    }

    public String getDocuID() {
        return docuID;
    }

    public String getDocuName() {
        return docuName;
    }

    public String getDocuAuth() {
        return docuAuth;
    }

    public String getChildRes() {
        return childRes;
    }

    public String getDocuLink() {
        return docuLink;
    }

    public String getDocType() {
        return docType;
    }

    public String getUserID() {
        return userID;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public void setDocuID(String docuID) {
        this.docuID = docuID;
    }

    public void setDocuName(String docuName) {
        this.docuName = docuName;
    }

    public void setDocuAuth(String docuAuth) {
        this.docuAuth = docuAuth;
    }

    public void setChildRes(String childRes) {
        this.childRes = childRes;
    }

    public void setDocuLink(String docuLink) {
        this.docuLink = docuLink;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
