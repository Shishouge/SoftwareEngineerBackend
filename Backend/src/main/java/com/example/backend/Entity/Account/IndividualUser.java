package com.example.backend.Entity.Account;

public class IndividualUser {
    private String ID;
    private String USER_NAME;
    private String INTRODUCTION;
    private String AVATOR;
    private int STATUS;
    private String token;


    public IndividualUser(String ID, String USER_NAME, String INTRODUCTION, String AVATOR, int STATUS) {
        this.ID = ID;
        this.USER_NAME = USER_NAME;
        this.INTRODUCTION = INTRODUCTION;
        this.AVATOR = AVATOR;
        this.STATUS = STATUS;
    }

    public IndividualUser() {
    }

    public String getINTRODUCTION() {
        return INTRODUCTION;
    }

    public void setINTRODUCTION(String INTRODUCTION) {
        this.INTRODUCTION = INTRODUCTION;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getAVATOR() {
        return AVATOR;
    }

    public void setAVATOR(String AVATOR) {
        this.AVATOR = AVATOR;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
