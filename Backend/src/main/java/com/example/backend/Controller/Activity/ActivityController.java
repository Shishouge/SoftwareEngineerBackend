package com.example.backend.Controller.Activity;

import cn.dev33.satoken.stp.StpUtil;
import com.example.backend.DAO.Activity.ActivityMapper;
import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Account.Organization;
import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Activity.ActivityHelper;
import com.example.backend.Entity.Activity.EmotionAnalysis;
import com.example.backend.Entity.Activity.ReviewActivity;
import com.example.backend.Service.Activity.ActivityService;
import com.example.backend.Util.Response.AjaxJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.entity.ContentType;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.E;

@CrossOrigin
@RestController
@Api(tags = "ActivityController")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivityMapper activityMapper;
    @ApiOperation(value = "获得所有活动信息")
    @RequestMapping(value = "/getAllActivities",method = RequestMethod.GET)
    public AjaxJson getAllActivities()
    {
        List<Activity> activities=activityService.getAllActivities();
        List<ActivityHelper> helpers=new ArrayList<>();
        for(int i=0;i<activities.size();i++)
        {
            Activity a=activities.get(i);
            ActivityHelper helper=new ActivityHelper(a.getID(),a.getTitle(),a.getImg(),a.getDate(),a.getPlace(),a.getForm(),a.getActivityIntroduction(),a.getContent(),a.getGenres(),a.getLikeNum(),a.getCapacity(),a.getStatus(),a.getSubscriberNum(),new Organization(a.getOrganizationID(),a.getOrganizerName(),a.getOrganizerIntro(),a.getAvator(),a.getOrganizerStatus()));
            helpers.add(helper);
        }
        if(activities.size()==0)
            return new AjaxJson(200,"数据库不存在该信息",null,0L);
        else
            return new AjaxJson(200,"查询成功",helpers,(long)helpers.size());

    }

    @ApiOperation(value = "根据ID获取某一活动的细节")
    @RequestMapping(value = "/getActivityDetail",method = RequestMethod.GET)
    public AjaxJson getActivityDetail(int ID)
    {
        Activity a=activityMapper.getLikeNum(ID);
        ActivityHelper helper=new ActivityHelper(a.getID(),a.getTitle(),a.getImg(),a.getDate(),a.getPlace(),a.getForm(),a.getActivityIntroduction(),a.getContent(),a.getGenres(),a.getLikeNum(),a.getCapacity(),a.getStatus(),a.getSubscriberNum(),new Organization(a.getOrganizationID(),a.getOrganizerName(),a.getOrganizerIntro(),a.getAvator(),a.getOrganizerStatus()));
        if(a==null)
            return new AjaxJson(200,"数据库不存在该信息",null,0L);
        else
            return new AjaxJson(200,"查询成功",helper,1L);
    }

    @ApiOperation(value = "获得某一活动的评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ID",value = "活动",required = true)
    })
    @RequestMapping(value = "/getReviewsByActivity",method = RequestMethod.GET)
    public AjaxJson getReviewsByActivity(int ID)
    {
        List<ReviewActivity> reviewActivities=activityService.getReviewsByActivity(ID);
        if(reviewActivities.size()==0)
            return new AjaxJson(200,"数据库不存在该信息",reviewActivities,0L);
        else
            return new AjaxJson(200,"查询成功",reviewActivities,(long)reviewActivities.size());
    }
    //String title,String img,String organizationID,String date,String place,String form,String introduction,String content,String genres,int capacity,int status

    @ApiOperation(value = "修改活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID",value = "活动ID"),
            @ApiImplicitParam(name = "title",value="标题"),
            @ApiImplicitParam(name = "img",value="图片地址"),
            @ApiImplicitParam(name = "organizationID",value="发布组织ID"),
            @ApiImplicitParam(name = "date",value="日期"),
            @ApiImplicitParam(name = "place",value="举办地点"),
            @ApiImplicitParam(name = "form",value="举办形式"),
            @ApiImplicitParam(name = "introduction",value="简介"),
            @ApiImplicitParam(name = "content",value="内容"),
            @ApiImplicitParam(name = "genres",value="标签"),
            @ApiImplicitParam(name = "capacity",value="容量"),
            @ApiImplicitParam(name = "status",value="活动状态")
    })
    @RequestMapping(value = "/updateActivity",method = RequestMethod.POST)
    public AjaxJson updateActivity(int ID,String title,String img,String organizationID,String date,String place,String form,String introduction,String content,String genres,int capacity,int status)
    {
        int result=activityService.updateActivity(ID,title,img,organizationID,date,place,form,introduction,content,genres,capacity,status);
        if(result==0)
            return new AjaxJson(200,"修改失败",result,0L);
        else
            return new AjaxJson(200,"修改成功",result,1L);
    }

    @ApiOperation(value = "点赞活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "individualUserID",value = "点赞者ID"),
            @ApiImplicitParam(name = "activityID",value = "活动ID")
    })
    @RequestMapping(value = "/likeActivity",method = RequestMethod.POST)
    public AjaxJson likeActivity(String individualUserID,int activityID)
    {
        int result=activityService.likeActivity(individualUserID,activityID);
        if(result==0)
            return new AjaxJson(200,"插入失败",result,0L);
        else
            return new AjaxJson(200,"插入成功",result,1L);
    }

    @ApiOperation(value = "取消点赞活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "individualUserID",value = "点赞者ID"),
            @ApiImplicitParam(name = "activityID",value = "活动ID")
    })
    @RequestMapping(value = "/unlikelikeActivity",method = RequestMethod.POST)
    public AjaxJson unlikeActivity(String individualUserID,int activityID)
    {
        int result=activityService.unlike(individualUserID,activityID);
        if(result==0)
            return new AjaxJson(200,"取消失败",result,0L);
        else
            return new AjaxJson(200,"取消成功",result,1L);
    }

    @ApiOperation(value = "评论活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "individualUserID",value = "评论者ID"),
            @ApiImplicitParam(name = "activityID",value = "活动ID"),
            @ApiImplicitParam(name = "content",value = "内容"),
            @ApiImplicitParam(name = "score",value = "评分")
    })
    @RequestMapping(value = "/reviewActivity",method = RequestMethod.POST)
    public AjaxJson reviewActivity(String individualUserID,int activityID,String content,int score)
    {
        int result=activityService.review(individualUserID,activityID,content,score);
        if(result==0)
            return new AjaxJson(200,"该用户还未报名活动，无法评论",result,0L);
        else
            return new AjaxJson(200,"评论成功",result,1L);
    }

    @ApiOperation(value = "报名活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "individualUserID",value = "报名者ID"),
            @ApiImplicitParam(name = "activityID",value = "活动ID"),
            @ApiImplicitParam(name="token",value="验证")
    })
    @RequestMapping(value = "/signUpActivity",method = RequestMethod.POST)
    public AjaxJson signUpActivity(String individualUserID,int activityID,  String token)
    {
        if(token.equals(StpUtil.getTokenValue()))
        {
            int result=activityService.signUpActivity(individualUserID, activityID);
            if(result==0)
                return new AjaxJson(200,"插入失败",result,0L);
            else
                return new AjaxJson(200,"插入成功",result,1L);
        }
        else {
            return new AjaxJson(200,"未登录！",null,0L);
        }

    }

    @ApiOperation(value = "获得推荐的活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID",value = "被推荐用户的邮箱")
    })
    @RequestMapping(value = "/getRecommendActivity",method = RequestMethod.GET)
    public AjaxJson getRecommendActivity(String ID)
    {
        List<Activity> activities=activityService.getRecommendActivity(ID);
        List<ActivityHelper> helpers=new ArrayList<>();
        for(int i=0;i<activities.size();i++)
        {
            Activity a=activities.get(i);
            ActivityHelper helper=new ActivityHelper(a.getID(),a.getTitle(),a.getImg(),a.getDate(),a.getPlace(),a.getForm(),a.getActivityIntroduction(),a.getContent(),a.getGenres(),a.getLikeNum(),a.getCapacity(),a.getStatus(),a.getSubscriberNum(),new Organization(a.getOrganizationID(),a.getOrganizerName(),a.getOrganizerIntro(),a.getAvator(),a.getOrganizerStatus()));
            helpers.add(helper);
        }
        if(activities.size()==0)
            return new AjaxJson(200,"数据库不存在该信息",helpers,0L);
        else
            return new AjaxJson(200,"查询成功",helpers,(long)helpers.size());
    }

    @ApiOperation("查看某人是否点赞过某活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "individualUserID",value = "个人用户ID"),
            @ApiImplicitParam(name = "activityID",value = "活动ID")
    })
    @RequestMapping(value = "/checkLike",method = RequestMethod.GET)
    public AjaxJson checkLike(String individualUserID,int activityID)
    {
        int r= activityService.checkLike(individualUserID, activityID);
        if(r==0)
            return new AjaxJson(200,"未点赞",r,0L);
        else
            return new AjaxJson(200,"已经点赞过",r,1L);
    }

    @ApiOperation("查看某人是否报名过某活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "individualUserID",value = "个人用户ID"),
            @ApiImplicitParam(name = "activityID",value = "活动ID")
    })
    @RequestMapping(value = "/checkSignUp",method = RequestMethod.GET)
    public AjaxJson checkSignUp(String individualUserID,int activityID)
    {
        int r= activityService.checkSignUp(individualUserID, activityID);
        if(r==0)
            return new AjaxJson(200,"未报名",r,0L);
        else
            return new AjaxJson(200,"已报名",r,1L);
    }
    @ApiOperation("取消报名活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "individualUserID",value = "个人用户ID"),
            @ApiImplicitParam(name = "activityID",value = "活动ID")
    })
    @RequestMapping(value = "/cancleSignUp",method = RequestMethod.POST)
    public AjaxJson cancleSignUp(String individualUserID,int activityID)
    {
        int r=activityService.cancleSignUp(individualUserID, activityID);
        if(r==1)
            return new AjaxJson(200,"取消报名成功",r,0L);
        else
            return new AjaxJson(200,"取消失败",r,1L);
    }

    @ApiOperation("获取某活动评论的情感分析")
    @ApiImplicitParam(name = "ID",value = "活动ID")
    @RequestMapping(value = "/getEmotionalAnalysis",method = RequestMethod.GET)
    public AjaxJson getEmotionalAnalysis(int ID)
    {
        EmotionAnalysis e =activityService.getEmotionalAnalysis(ID);
        return new AjaxJson(200,"成功",e,0L);
    }

    @ApiOperation("删除活动")
    @ApiImplicitParam(name = "ID",value = "活动ID")
    @RequestMapping(value = "/deleteActivity",method = RequestMethod.POST)
    public AjaxJson deleteActivity(int ID)
    {
        int r=activityService.deleteActivity(ID);
        if(r==1)
            return new AjaxJson(200,"删除成功",r,1L);
        else
            return new AjaxJson(200,"删除失败",r,0L);
    }

    @ApiOperation("获得某活动的报名者信息")
    @ApiImplicitParam(name = "ID",value = "活动ID")
    @RequestMapping(value = "/getUserSubscribed",method = RequestMethod.GET)
    public AjaxJson getUserSubscribed(int ID)
    {
        List<IndividualUser> individualUsers=activityService.getUserSubscribed(ID);
        if(individualUsers.size()==0)
        {
            return new AjaxJson(200,"数据库无此信息",individualUsers,0L);
        }
        else
        {
            return new AjaxJson(200,"查询成功",individualUsers,(long)individualUsers.size());
        }
    }

    @ApiOperation("删除某活动的评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iID",value = "评论发布者ID"),
            @ApiImplicitParam(name = "aID",value = "活动ID")
    })
    @RequestMapping(value = "/deleteReview",method = RequestMethod.POST)
    public AjaxJson deleteReview(String iID,int aID)
    {
        int r=activityService.deleteReview(iID,aID);
        if(r==0)
            return new AjaxJson(200,"删除失败",r,0L);
        else
            return new AjaxJson(200,"删除成功",r,1L);
    }

    @ApiOperation("筛选活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name="genres",value="活动类型"),
            @ApiImplicitParam(name="status",value="是否在进行"),
            @ApiImplicitParam(name="isAbleToRe",value="是否可报名"),
            @ApiImplicitParam(name="key",value="搜索关键词")
    })
    @RequestMapping(value = "/filterActivity",method = RequestMethod.GET)
    public AjaxJson filterActivity(String genres,String status,String isAbleToRe,String key)
    {
        Integer num=null;
        if(isAbleToRe!=null)
            num=1;
        List<Activity> activities=activityService.filterActivity(genres,status,num,key);
        List<ActivityHelper> helpers=new ArrayList<>();
        for(int i=0;i<activities.size();i++)
        {
            Activity a=activities.get(i);
            ActivityHelper helper=new ActivityHelper(a.getID(),a.getTitle(),a.getImg(),a.getDate(),a.getPlace(),a.getForm(),a.getActivityIntroduction(),a.getContent(),a.getGenres(),a.getLikeNum(),a.getCapacity(),a.getStatus(),a.getSubscriberNum(),new Organization(a.getOrganizationID(),a.getOrganizerName(),a.getOrganizerIntro(),a.getAvator(),a.getOrganizerStatus()));
            helpers.add(helper);
        }
        if(activities.size()==0)
            return new AjaxJson(200,"数据库不存在该信息",helpers,0L);
        else
            return new AjaxJson(200,"查询成功",helpers,(long)helpers.size());
    }
}
