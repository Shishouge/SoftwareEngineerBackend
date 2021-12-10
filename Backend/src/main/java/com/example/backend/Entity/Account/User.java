package com.example.backend.Entity.Account;

public abstract class User {
    private String USERNAME;
    private String AVATOR;

    public User(String USEREMAIL, String AVATOR) {
        this.USERNAME = USEREMAIL;
        this.AVATOR = AVATOR;
    }


    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSER_NAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getAVATOR() {
        return AVATOR;
    }

    public void setAVATOR(String AVATOR) {
        this.AVATOR = AVATOR;
    }
}
