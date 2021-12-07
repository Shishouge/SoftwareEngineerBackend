package com.example.backend.Entity.Account;

public class IndividualUser extends User{
    private String INTRODUCTION;

    public IndividualUser(String ID, String USER_NAME, String AVATOR, String INTRODUCTION) {
        super(ID, USER_NAME, AVATOR);
        this.INTRODUCTION = INTRODUCTION;
    }

    public String getINTRODUCTION() {
        return INTRODUCTION;
    }

    public void setINTRODUCTION(String INTRODUCTION) {
        this.INTRODUCTION = INTRODUCTION;
    }
}
