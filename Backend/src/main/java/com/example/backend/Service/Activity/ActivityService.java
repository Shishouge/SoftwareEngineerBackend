package com.example.backend.Service.Activity;

import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Activity.ReviewActivity;

import java.util.List;

public interface ActivityService {
    //获得所有活动
    public List<Activity> getAllActivities();
    //获得某一活动的评论
    public List<ReviewActivity> getReviewsByActivity(int ID);
    //发布活动
    public int publishActivity(String title,String img,String organizationID,String date,String place,String form,String introduction,String content,String genres,int capacity,int status);
    //修改活动
    public int updateActivity(int ID,String title,String img,String organizationID,String date,String place,String form,String introduction,String content,String genres,int capacity,int status);
    //点赞活动
    public int likeActivity(String individualUserID,int activityID);
    //取消点赞活动
    public int unlike(String individualUserID,int activityID);
    //评论活动
    public int review(String individualUserID,int activityID,String content,int score);
    //报名活动
    public int signUpActivity(String individualUserID,int activityID);
    //获取推荐活动列表
    public List<Activity> getRecommendActivity(String ID);
}
