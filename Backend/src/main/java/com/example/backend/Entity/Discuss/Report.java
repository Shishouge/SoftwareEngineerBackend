package com.example.backend.Entity.Discuss;

public class Report {
    private String WhistleblowerID;
    private String isReportedID;
    private String reason;

    public Report(String whistleblowerID, String isReportedID, String reason) {
        WhistleblowerID = whistleblowerID;
        this.isReportedID = isReportedID;
        this.reason = reason;
    }

    public String getWhistleblowerID() {
        return WhistleblowerID;
    }

    public void setWhistleblowerID(String whistleblowerID) {
        WhistleblowerID = whistleblowerID;
    }

    public String getIsReportedID() {
        return isReportedID;
    }

    public void setIsReportedID(String isReportedID) {
        this.isReportedID = isReportedID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
