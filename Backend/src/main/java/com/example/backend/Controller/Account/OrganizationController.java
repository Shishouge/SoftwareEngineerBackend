package com.example.backend.Controller.Account;

import com.example.backend.Entity.Account.Organization;
import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Activity.ActivityHelper;
import com.example.backend.Service.Account.OrganizationService;
import com.example.backend.Util.Response.AjaxJson;
import com.example.backend.Util.Response.ResponseCode;
import com.example.backend.Util.Response.ResponseResult;
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
@Api(tags = "Organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;
    @ApiOperation("登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ID",value = "组织ID",defaultValue = "2872529770@qq.com",required = true),
            @ApiImplicitParam(name="password",value = "组织密码",defaultValue = "111",required = true)
    })
    @RequestMapping(value = "/orglogin",method = RequestMethod.GET)
    public AjaxJson login(int ID, String password)
    {
        Organization check=organizationService.getOrgByID(ID);
        if(check==null)
            return new AjaxJson(400,"该组织还未注册",null,0L);
        else
        {
            if(check.getSTATUS()==0)
            {
                return new AjaxJson(500,"该组织还未通过审核",null,0L);
            }
            else
            {
                Organization organization=organizationService.getByUserAndPassword(ID,password);
                if(organization==null)
                    return new AjaxJson(500,"密码错误",null,0L);
                return new AjaxJson(200,"登录成功",organization,1L);
            }

        }

    }

    @ApiOperation("注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName",value = "组织名",defaultValue = "2872529770@qq.com",required = true),
            @ApiImplicitParam(name="password",value = "组织密码",defaultValue = "111",required = true),
            @ApiImplicitParam(name = "introduction",value="简介"),
            @ApiImplicitParam(name = "avator",value = "头像"),
            @ApiImplicitParam(name = "certification",value = "证明材料")
    })
    @RequestMapping(value = "/signUp",method = RequestMethod.GET)
    public AjaxJson signUp(String userName,String password,String introduction,String avator,String certification)
    {
        Organization check=organizationService.getOrgByName(userName);
        int insert=-2;
        if(check!=null)
        {
            return new AjaxJson(500,"该组织已经注册",null,0L);
        }
        else
        {
            insert=organizationService.insertOrganization(userName, password, introduction, avator, certification);
            if(insert==0)
                return new AjaxJson(500,"注册失败",null,0L);
            else
                return new AjaxJson(200,"注册成功",insert,1L);
        }
    }


    @ApiOperation("查询某组织发布的活动")
    @ApiImplicitParam(name = "ID",value = "组织者ID")
    @RequestMapping(value = "/getAllActivityByOrg",method = RequestMethod.GET)
    public AjaxJson getAllActivityByOrg(int ID)
    {
        List<Activity> activities=organizationService.getAllActivityByOrg(ID);
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
}
