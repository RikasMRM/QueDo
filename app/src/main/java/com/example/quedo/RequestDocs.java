package com.example.quedo;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RequestDocs {
    String docType, docName, docAuth, userID;

    public RequestDocs() { }

    public RequestDocs(String docType, String docName, String docAuth, String userID) {
        this.docType = docType;
        this.docName = docName;
        this.docAuth = docAuth;
        this.userID = userID;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocAuth() {
        return docAuth;
    }

    public void setDocAuth(String docAuth) {
        this.docAuth = docAuth;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
