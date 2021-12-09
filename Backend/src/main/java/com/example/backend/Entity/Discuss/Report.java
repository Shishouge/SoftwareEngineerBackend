package com.example.backend.Entity.Discuss;

public class Report {
    private int WhistleblowerID;
    private int isReportedID;
    private String reason;

    public Report(int whistleblowerID, int isReportedID, String reason) {
        WhistleblowerID = whistleblowerID;
        this.isReportedID = isReportedID;
        this.reason = reason;
    }

    public int getWhistleblowerID() {
        return WhistleblowerID;
    }

    public void setWhistleblowerID(int whistleblowerID) {
        WhistleblowerID = whistleblowerID;
    }

    public int getIsReportedID() {
        return isReportedID;
    }

    public void setIsReportedID(int isReportedID) {
        this.isReportedID = isReportedID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
