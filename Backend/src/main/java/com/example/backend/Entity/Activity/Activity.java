package com.example.backend.Entity.Activity;

public class Activity {
    private int ID;
    private String title;
    private String img;

    private int organizationID;
    private String organizerName;
    private String organizerIntro;
    private String avator;
    private int organizerStatus;

    private String date;
    private String place;
    private String form;
    private String activityIntroduction;
    private String content;
    private String genres;
    private int likeNum;
    private int capacity;
    private int status;
    private int subscriberNum;
//    private String userName;


    public Activity(int ID, String title, String img, int organizationID, String organizerName, String organizerIntro, String avator, int organizerStatus, String date, String place, String form, String introduction, String content, String genres, int likeNum, int capacity, int status, int subscriberNum) {
        this.ID = ID;
        this.title = title;
        this.img = img;
        this.organizationID = organizationID;
        this.organizerName = organizerName;
        this.organizerIntro = organizerIntro;
        this.avator = avator;
        this.organizerStatus = organizerStatus;
        this.date = date;
        this.place = place;
        this.form = form;
        this.activityIntroduction = introduction;
        this.content = content;
        this.genres = genres;
        this.likeNum = likeNum;
        this.capacity = capacity;
        this.status = status;
        this.subscriberNum = subscriberNum;
    }

    public Activity() {
    }

    public Activity(String date,String form) {
        this.date = date;
        this.form=form;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getActivityIntroduction() {
        return activityIntroduction;
    }

    public void setActivityIntroduction(String activityIntroduction) {
        this.activityIntroduction = activityIntroduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getOrganizerIntro() {
        return organizerIntro;
    }

    public void setOrganizerIntro(String organizerIntro) {
        this.organizerIntro = organizerIntro;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public int getSubscriberNum() {
        return subscriberNum;
    }

    public void setSubscriberNum(int subscriberNum) {
        this.subscriberNum = subscriberNum;
    }


    public int getOrganizerStatus() {
        return organizerStatus;
    }

    public void setOrganizerStatus(int organizerStatus) {
        this.organizerStatus = organizerStatus;
    }
}
