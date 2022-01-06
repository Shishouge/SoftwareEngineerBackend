package com.example.backend.Entity.Activity;

import com.example.backend.Entity.Account.OrgHelper;
import com.example.backend.Entity.Account.Organization;

public class ActivityHelper {
    private int ID;
    private String title;
    private String img;
    private String date;
    private String place;
    private String form;
    private String introduction;
    private String content;
    private String genres;
    private int likeNum;
    private int capacity;
    private int status;
    private int subscriberNum;
    private OrgHelper organizer;

    public ActivityHelper(int ID, String title, String img, String date, String place, String form, String introduction, String content, String genres, int likeNum, int capacity, int status, int subscriber,OrgHelper organizer) {
        this.ID = ID;
        this.title = title;
        this.img = img;
        this.date = date;
        this.place = place;
        this.form = form;
        this.introduction = introduction;
        this.content = content;
        this.genres = genres;
        this.likeNum = likeNum;
        this.capacity = capacity;
        this.status = status;
        this.subscriberNum=subscriber;
        this.organizer = organizer;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getForm() {
        return form;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getContent() {
        return content;
    }

    public String getGenres() {
        return genres;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getStatus() {
        return status;
    }

    public int getSubscriberNum() {
        return subscriberNum;
    }

    public void setSubscriberNum(int subscriberNum) {
        this.subscriberNum = subscriberNum;
    }

    public OrgHelper getOrganizer() {
        return organizer;
    }
}
