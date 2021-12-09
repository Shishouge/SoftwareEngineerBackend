package com.example.backend.Entity.Discuss;

public class Question {
    private int ID;
    private String IndividualUserID;
    private String title;
    private String content;

    public Question(int ID, String individualUserID, String title, String content) {
        this.ID = ID;
        IndividualUserID = individualUserID;
        this.title = title;
        this.content = content;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIndividualUserID() {
        return IndividualUserID;
    }

    public void setIndividualUserID(String individualUserID) {
        IndividualUserID = individualUserID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
