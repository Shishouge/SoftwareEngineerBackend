package com.example.backend.Entity.Activity;

public class ReviewActivity {
    private int reviewerID;
    private int activityID;
    private String content;
    private int score;
    private String reviewerName;
    private String reviewerIntroduction;
    private String reviewerAvator;


    public ReviewActivity(int individualUserID, int activityID, String content, int score, String userName, String introduction, String avator) {
        this.reviewerID = individualUserID;
        this.activityID = activityID;
        this.content = content;
        this.score = score;
        this.reviewerName = userName;
        this.reviewerIntroduction = introduction;
        this.reviewerAvator = avator;
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerIntroduction() {
        return reviewerIntroduction;
    }

    public void setReviewerIntroduction(String reviewerIntroduction) {
        this.reviewerIntroduction = reviewerIntroduction;
    }

    public String getReviewerAvator() {
        return reviewerAvator;
    }

    public void setReviewerAvator(String reviewerAvator) {
        this.reviewerAvator = reviewerAvator;
    }

}
