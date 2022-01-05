package com.example.backend.Entity.Discuss;

public class Report {
    private String WhistleblowerID;
    private String isReportedID;
    private String reason;
    private String question_ID;
    private String answer_ID;

    public Report(String whistleblowerID, String isReportedID, String reason) {
        WhistleblowerID = whistleblowerID;
        this.isReportedID = isReportedID;
        this.reason = reason;
    }

    public Report(String whistleblowerID, String isReportedID, String reason, String question_ID, String answer_ID) {
        WhistleblowerID = whistleblowerID;
        this.isReportedID = isReportedID;
        this.reason = reason;
        this.question_ID = question_ID;
        this.answer_ID = answer_ID;
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

    public String getQuestion_ID() {
        return question_ID;
    }

    public void setQuestion_ID(String question_ID) {
        this.question_ID = question_ID;
    }

    public String getAnswer_ID() {
        return answer_ID;
    }

    public void setAnswer_ID(String answer_ID) {
        this.answer_ID = answer_ID;
    }
}
