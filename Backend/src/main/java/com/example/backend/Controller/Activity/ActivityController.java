package com.example.backend.Controller.Activity;

import com.example.backend.DAO.Activity.ActivityMapper;
import com.example.backend.Entity.Account.Organization;
import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Activity.ActivityHelper;
import com.example.backend.Entity.Activity.ReviewActivity;
import com.example.backend.Service.Activity.ActivityService;
import com.example.backend.Util.Response.AjaxJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "活动接口")
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
            return new AjaxJson(500,"数据库不存在该信息",null,0L);
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
            return new AjaxJson(500,"数据库不存在该信息",null,0L);
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
            return new AjaxJson(500,"数据库不存在该信息",null,0L);
        else
            return new AjaxJson(200,"查询成功",reviewActivities,(long)reviewActivities.size());
    }
    //String title,String img,String organizationID,String date,String place,String form,String introduction,String content,String genres,int capacity,int status
    @ApiOperation(value = "发布活动")
    @ApiImplicitParams({
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
    @RequestMapping(value = "/publishActivity",method = RequestMethod.POST)
    public AjaxJson publishActivity(String title, String img, String organizationID, String date, String place, String form, String introduction, String content, String genres, int capacity, int status)
    {
        int result=activityService.publishActivity(title,img,organizationID,date,place,form,introduction,content,genres,capacity,status);
        if(result==0)
            return new AjaxJson(500,"插入失败",null,0L);
        else
            return new AjaxJson(200,"插入成功",result,1L);
    }
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
            return new AjaxJson(500,"修改失败",null,0L);
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
            return new AjaxJson(500,"插入失败",null,0L);
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
            return new AjaxJson(500,"取消失败",null,0L);
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
            return new AjaxJson(500,"该用户还未报名活动，无法评论",null,0L);
        else
            return new AjaxJson(200,"评论成功",result,1L);
    }

    @ApiOperation(value = "报名活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "individualUserID",value = "报名者ID"),
            @ApiImplicitParam(name = "activityID",value = "活动ID")
    })
    @RequestMapping(value = "/signUpActivity",method = RequestMethod.GET)
    public AjaxJson signUpActivity(String individualUserID,int activityID)
    {
        int result=activityService.signUpActivity(individualUserID, activityID);
        if(result==0)
            return new AjaxJson(500,"插入失败",null,0L);
        else
            return new AjaxJson(200,"插入成功",result,1L);
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
            return new AjaxJson(500,"数据库不存在该信息",null,0L);
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
    @RequestMapping(value = "/cancleSignUp",method = RequestMethod.GET)
    public AjaxJson cancleSignUp(String individualUserID,int activityID)
    {
        int r=activityService.cancleSignUp(individualUserID, activityID);
        if(r==1)
            return new AjaxJson(200,"取消报名成功",r,0L);
        else
            return new AjaxJson(500,"取消失败",r,1L);
    }

}
