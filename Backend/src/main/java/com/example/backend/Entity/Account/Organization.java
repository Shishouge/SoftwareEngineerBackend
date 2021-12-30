package com.example.backend.Entity.Account;

public class Organization{
    private int ID;
    private String USERNAME;
    private String INTRODUCTION;
    private String AVATOR;
    private int STATUS;
    private String CERTIFICATION;


    public Organization(int ID, String USERNAME, String INTRODUCTION, String AVATOR, int STATUS) {
        this.ID = ID;
        this.USERNAME = USERNAME;
        this.INTRODUCTION = INTRODUCTION;
        this.AVATOR = AVATOR;
        this.STATUS = STATUS;
    }

    public Organization(int ID, String USERNAME, String INTRODUCTION, String AVATOR, int STATUS, String CERTIFICATION) {
        this.ID = ID;
        this.USERNAME = USERNAME;
        this.INTRODUCTION = INTRODUCTION;
        this.AVATOR = AVATOR;
        this.STATUS = STATUS;
        this.CERTIFICATION = CERTIFICATION;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getAVATOR() {
        return AVATOR;
    }

    public void setAVATOR(String AVATOR) {
        this.AVATOR = AVATOR;
    }

    public String getCERTIFICATION() {
        return CERTIFICATION;
    }

    public void setCERTIFICATION(String CERTIFICATION) {
        this.CERTIFICATION = CERTIFICATION;
    }
}
