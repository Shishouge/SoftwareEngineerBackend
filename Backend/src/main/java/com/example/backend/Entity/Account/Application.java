package com.example.backend.Entity.Account;

public class Application {
    private int organizationID;
    private String password;
    private String userName;
    private String introduction;
    private String avator;
    private String certification;
    private String AdminID;

    public Application(int ID, String password, String userName, String introduction, String avator, String certification, String adminID) {
        this.organizationID = ID;
        this.password = password;
        this.userName = userName;
        this.introduction = introduction;
        this.avator = avator;
        this.certification = certification;
        AdminID = adminID;
    }

    public int getID() {
        return organizationID;
    }

    public void setID(int ID) {
        this.organizationID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String adminID) {
        AdminID = adminID;
    }
}
