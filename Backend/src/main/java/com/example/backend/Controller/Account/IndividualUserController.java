package com.example.backend.Controller.Account;

import cn.dev33.satoken.stp.StpUtil;
import com.example.backend.DAO.Account.IndividualUserMapper;
import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Account.OrgHelper;
import com.example.backend.Entity.Account.Organization;
import com.example.backend.Entity.Account.User;
import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Activity.ActivityHelper;
import com.example.backend.Entity.Discuss.Question;
import com.example.backend.Entity.Discuss.QuestionHelper;
import com.example.backend.Service.Account.IndividualUserService;
import com.example.backend.Util.Email.VerifyEmailUtil;
import com.example.backend.Util.Response.AjaxJson;
import com.example.backend.Util.Response.ResponseCode;
import com.example.backend.Util.Response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin
@Api(tags = "IndividualUserSystem")
@RestController
public class IndividualUserController {
    @Autowired
    IndividualUserService individualUserService;
    @Autowired
    IndividualUserMapper individualUserMapper;
    @Autowired
    VerifyEmailUtil verifyEmailUtil;
    @ApiOperation("登录接口，传入邮箱和密码，返回true/false")
    @ApiImplicitParams({
            @ApiImplicitParam(name="email",value = "用户邮箱",defaultValue = "11",required = true),
            @ApiImplicitParam(name="password",value = "用户密码",defaultValue = "22",required = true)
    })
    @RequestMapping(value="/login",method = {RequestMethod.GET})
    public AjaxJson login(String email, String password)
    {
        IndividualUser individualUser=individualUserService.getByEmail(email);
        if(individualUser==null)
            return new AjaxJson(200,"该用户尚未注册",null,0L);
        else
        {
            if(individualUser.getSTATUS()==0)
            {
                return new AjaxJson(200,"该用户目前处于封禁状态",null,0L);
            }
            IndividualUser result=individualUserService.loginByEmail(email,password);
            if(result==null)
                return new AjaxJson(200,"密码错误",null,0L);
            else
            {
                StpUtil.login(email);
                result.setToken(StpUtil.getTokenValue());
                return new AjaxJson(200,"登录成功",result,1L);
            }

        }

    }

    @ApiOperation("注册接口，传入邮箱，用户名，密码，返回注册信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="email",value="用户邮箱",required = true),
            @ApiImplicitParam(name="name",value="用户名称",required = true),
            @ApiImplicitParam(name="password",value="用户密码",required = true),
    })
    @RequestMapping(value="/signUp",method = {RequestMethod.POST})
    public AjaxJson signUp(String email,String name,String password)
    {
        IndividualUser check=individualUserService.getByEmail(email);
        int insert=-2;
        if(check==null)
        {
            insert=individualUserService.insertUser(email,password,name);
        }
        String result;
        if(check==null&&insert==1)
            return new AjaxJson(200,"注册成功",insert,1L);
        else if(check!=null)
            return new AjaxJson(200,"该账号已被注册",insert,0L);
        else
            return new AjaxJson(200,"数据库插入失败",insert,0L);
    }

    @ApiOperation("修改个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="email",value = "用户邮箱",required = true),
            @ApiImplicitParam(name = "name",value = "用户名"),
            @ApiImplicitParam(name = "password",value = "登录密码"),
            @ApiImplicitParam(name = "introduction",value = "个人介绍"),
            @ApiImplicitParam(name="avator",value = "头像地址"),
    })
    @RequestMapping(value = "/editInformation",method = RequestMethod.POST)
    public AjaxJson editInformation(String email,String name,String password,String introduction,String avator)
    {
        int update=individualUserService.editInformation(email,name,password,introduction,avator);
        if(update==0)
            return new AjaxJson(200,"修改失败",update,0L);
        else
            return new AjaxJson(200,"修改成功",update,1L);
    }


    @ApiOperation("发送验证码")
    @RequestMapping(value = "/sendEmail",method={RequestMethod.POST})
    public AjaxJson sendEmail(HttpServletRequest request, String email)
    {
//        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
//        try {
//            individualUserService.sendEmail(to,checkCode);
//        }catch (Exception e){
//            return new AjaxJson(500,"发送失败",null,0L);
//        }
//        return new AjaxJson(200,"发送成功",checkCode,1L);
        return verifyEmailUtil.sendMail(request,email);
    }
    @ApiOperation("验证验证码")
    @RequestMapping(value = "/verifyCode",method={RequestMethod.GET})
    public AjaxJson verifyCode(String inputCode, HttpServletRequest request)
    {
        return verifyEmailUtil.checkCode(inputCode,request);
    }


    @ApiOperation("获得某人发布的问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID",value = "用户邮箱")
    })
    @RequestMapping(value = "/getMyQuestions",method = RequestMethod.GET)
    public AjaxJson getMyQuestions(String ID)
    {
        List<Question> questionList=individualUserService.getMyQuestions(ID);
        if(questionList.size()==0)
            return new AjaxJson(200,"数据库不存在有关信息",null,0L);
        else
            return new AjaxJson(200,"查询成功",questionList,(long)questionList.size());
    }

    @ApiOperation("获得某人关注的问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID",value = "用户邮箱")
    })
    @RequestMapping(value ="/getMyFocusQuestions",method = RequestMethod.GET)
    public AjaxJson getMyFocusQuestions(String ID)
    {
        List<QuestionHelper> questionList=individualUserService.getMyFocusQuestion(ID);
        if(questionList.size()==0)
            return new AjaxJson(200,"数据库不存在有关信息",null,0L);
        else
            return new AjaxJson(200,"查询成功",questionList,(long)questionList.size());
    }

    @ApiOperation("获得某人报名的活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email",value = "用户邮箱")
    })
    @RequestMapping(value ="/getMyActivities",method = RequestMethod.GET)
    public AjaxJson getMyActivities(String email)
    {
        List<Activity> activities=individualUserService.getMyActivities(email);
        List<ActivityHelper> helpers=new ArrayList<>();
        for(int i=0;i<activities.size();i++)
        {
            Activity a=activities.get(i);
            ActivityHelper helper=new ActivityHelper(a.getID(),a.getTitle(),a.getImg(),a.getDate(),a.getPlace(),a.getForm(),a.getActivityIntroduction(),a.getContent(),a.getGenres(),a.getLikeNum(),a.getCapacity(),a.getStatus(),a.getSubscriberNum(),new OrgHelper(a.getOrganizationID(),a.getOrganizerName(),a.getOrganizerIntro(),a.getAvator(),a.getOrganizerStatus()));
            helpers.add(helper);
        }
        if(activities.size()==0)
            return new AjaxJson(200,"数据库不存在该信息",null,0L);
        else
            return new AjaxJson(200,"查询成功",helpers,(long)helpers.size());
    }

    @ApiOperation("举报")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "wID",value = "举报者ID"),
            @ApiImplicitParam(name = "rID",value = "被举报者ID"),
            @ApiImplicitParam(name = "reason",value = "举报原因"),
            @ApiImplicitParam(name="qID",value="问题ID"),
            @ApiImplicitParam(name = "aID",value = "回答ID")
    })
    @RequestMapping(value ="/reportUser",method = RequestMethod.POST)
    public AjaxJson reportUser(String wID, String rID, String reason,String qID,String aID)
    {
        int r= individualUserService.reportUser(wID, rID, reason,qID,aID);
        if(r==0)
            return new AjaxJson(200,"插入失败",null,0L);
        else
            return new AjaxJson(200,"插入成功",r,1L);
    }

    @ApiOperation("加密")
    @RequestMapping(value = "/encode", method = RequestMethod.GET)
    public int encode()
    {
        return individualUserMapper.encode();
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/editPassword", method = RequestMethod.POST)
    public AjaxJson editPassword(String ID,String password)
    {
        int r= individualUserService.editPassword(ID, password);
        if(r==0)
            return new AjaxJson(200,"修改失败",null,0L);
        else
            return new AjaxJson(200,"插入成功",r,1L);
    }
}
