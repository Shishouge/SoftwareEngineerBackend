package com.example.backend.Util;

import com.example.backend.Entity.Account.Organization;

public class testcase {
    public int ID;
    public String password;
    public Organization organization;
    public Organization login;
    public String expectedResults;

    public testcase(int ID, String password, Organization organization, Organization login, String expectedResults) {
        this.ID = ID;
        this.password = password;
        this.organization = organization;
        this.login = login;
        this.expectedResults = expectedResults;
    }

    public testcase() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getLogin() {
        return login;
    }

    public void setLogin(Organization login) {
        this.login = login;
    }

    public String getExpectedResults() {
        return expectedResults;
    }

    public void setExpectedResults(String expectedResults) {
        this.expectedResults = expectedResults;
    }
}
