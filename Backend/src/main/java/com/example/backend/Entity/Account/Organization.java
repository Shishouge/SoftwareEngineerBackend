package com.example.backend.Entity.Account;

public class Organization extends User{
    private String INTRODUCTION;
    private int STATUS;
    private int ID;

    public Organization(int ID,String USER_NAME, String AVATOR, String INTRODUCTION) {
        super(USER_NAME,AVATOR);
        this.ID=ID;
        this.INTRODUCTION = INTRODUCTION;
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
