package com.example.backend.Entity.Account;

public abstract class User {
    private String ID;
    private String PASSWORD;
    private String USER_NAME;
    private String AVATOR;

    public User(String ID, String PASSWORD, String USER_EMAIL, String AVATOR) {
        this.ID = ID;
        this.PASSWORD = PASSWORD;
        this.USER_NAME = USER_EMAIL;
        this.AVATOR = AVATOR;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
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
}