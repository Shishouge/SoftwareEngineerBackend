package com.example.backend.Entity.Discuss;

public class QuestionHelper {
    private String individualUserID;
    private String USER_NAME;
    private String INTRODUCTION;
    private String AVATOR;
    private int question_ID;
    private String title;
    private String content;

    public QuestionHelper(String individualUserID, String USER_NAME, String INTRODUCTION, String AVATOR, int question_ID, String title, String content) {
        this.individualUserID = individualUserID;
        this.USER_NAME = USER_NAME;
        this.INTRODUCTION = INTRODUCTION;
        this.AVATOR = AVATOR;
        this.question_ID = question_ID;
        this.title = title;
        this.content = content;
    }

    public String getIndividualUserID() {
        return individualUserID;
    }

    public void setIndividualUserID(String individualUserID) {
        this.individualUserID = individualUserID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getINTRODUCTION() {
        return INTRODUCTION;
    }

    public void setINTRODUCTION(String INTRODUCTION) {
        this.INTRODUCTION = INTRODUCTION;
    }

    public String getAVATOR() {
        return AVATOR;
    }

    public void setAVATOR(String AVATOR) {
        this.AVATOR = AVATOR;
    }

    public int getQuestion_ID() {
        return question_ID;
    }

    public void setQuestion_ID(int question_ID) {
        this.question_ID = question_ID;
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
