package com.example.backend.Entity.Account;

public class IndividualUser extends User{
    private String INTRODUCTION;

    public IndividualUser(String ID, String PASSWORD, String USER_EMAIL, String AVATOR, String INTRODUCTION) {
        super(ID, PASSWORD, USER_EMAIL, AVATOR);
        this.INTRODUCTION = INTRODUCTION;
    }

    public String getINTRODUCTION() {
        return INTRODUCTION;
    }

    public void setINTRODUCTION(String INTRODUCTION) {
        this.INTRODUCTION = INTRODUCTION;
    }
}
