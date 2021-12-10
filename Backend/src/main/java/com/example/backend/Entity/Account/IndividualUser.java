package com.example.backend.Entity.Account;

public class IndividualUser extends User{
    private String INTRODUCTION;
    private int STATUS;
    private String ID;

    public IndividualUser(String ID, String USER_NAME, String AVATOR, String INTRODUCTION,int STATUS) {
        super(USER_NAME, AVATOR);
        this.INTRODUCTION = INTRODUCTION;
        this.STATUS=STATUS;
        this.ID=ID;
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
}
