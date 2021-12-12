package com.example.backend.DAO.Activity;

import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Activity.EmotionAnalysis;
import com.example.backend.Entity.Activity.ReviewActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ActivityMapper {
    //返回所有活动
    public List<Activity> getAllActivities();
    //返回某一活动的评论
    public List<ReviewActivity> getReviewsByActivity(int ID);
    //发布活动
    public int publishActivity(String title,String img,String organizationID,String date,String place,String form,String introduction,String content,String genres,int likeNum,int capacity,int status,int subscriberNum);
    //修改活动
    public int updateActivity(int ID,String title,String img,String organizationID,String date,String place,String form,String introduction,String content,String genres,int capacity,int status);
    //点赞活动
    public int likeActivity(String individualUserID,int activityID);
    //获取当前点赞数
    public Activity getLikeNum(int activityID);
    //变化点赞数
    public int addLikeNum(int activityID,int likeNum);
    //取消点赞
    public int unlike(String individualUserID,int activityID);
    //评价活动
    public int review(String individualUserID,int activityID,String content,int score);
    //查看某人是否报名过某活动
    public int checkSignUpSitua(String individualUserID,int activityID);
    //报名活动
    public int signUpActivity(String individualUserID,int activityID);

    //取消报名活动
    public int cancleSignUp(String individualUserID,int activityID);

    //返回某人没有报名过的活动
    public List<Activity> getNotSignUpActivity(String ID);
    //获取所有地点标签
    public List<String> getAllPlaces();
    //获取所有举办方标签
    public List<String> getAllOrgs();
    //返回某个活动的举办方
    public String getOrgByActivity(int ID);
    //变化报名数
    public int addSubscribNum(int activityID,int subNum);
    //查看某人是否点赞过某活动
    public int checkLike(String individualUserID,int activityID);
    //修改活动状态
    public int editStatus(int activityID,int toStatus);
    //添加评论分析
    public int addAnalysis(String path,int ID);
    //添加词云图
    public int addCloud(String path,int ID);
    //删除活动
    public int deleteActivity(int ID);
    //获得情感分析的直方图和词云图
    public EmotionAnalysis getIMGofActivity(int ID);
    //获得某活动的报名者信息
    public List<IndividualUser> getUserSubscribed(int ID);
    //删除某活动的某评论
    public int deleteReview(String iID,int aID);


}
