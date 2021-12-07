package com.example.backend.Entity.Account;

public class Organization extends User{
    private String INTRODUCTION;

    public Organization(String ID, String USER_NAME, String AVATOR, String INTRODUCTION) {
        super(ID,USER_NAME, AVATOR);
        this.INTRODUCTION = INTRODUCTION;
    }

    public String getINTRODUCTION() {
        return INTRODUCTION;
    }

    public void setINTRODUCTION(String INTRODUCTION) {
        this.INTRODUCTION = INTRODUCTION;
    }
}
