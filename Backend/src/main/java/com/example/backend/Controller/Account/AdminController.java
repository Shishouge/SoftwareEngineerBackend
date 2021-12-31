package com.example.backend.Controller.Account;

import com.example.backend.Entity.Account.Admin;
import com.example.backend.Entity.Account.Application;
import com.example.backend.Entity.Account.Organization;
import com.example.backend.Entity.Discuss.Report;
import com.example.backend.Service.Account.AdminService;
import com.example.backend.Util.Email.SendEmailUtil;
import com.example.backend.Util.Response.AjaxJson;
import com.example.backend.Util.Response.ResponseCode;
import com.example.backend.Util.Response.ResponseResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    SendEmailUtil sendEmailUtil;

    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID",value = "管理员ID"),
            @ApiImplicitParam(name = "password",value = "密码")
    })
    @RequestMapping(value = "/AdminLogin",method = RequestMethod.POST)
    public AjaxJson AdminLogin(int ID, String password)
    {
        Admin admin=adminService.login(ID, password);
        if(admin==null)
            return new AjaxJson(500,"不存在该账号",null, 0L);
        else
            return new AjaxJson(200,"登录成功",admin, 1L);
    }

    @ApiOperation("获得管理员的信息")
    @PostMapping("/getDetailInformation")
    public Admin getDetailInformation(int id ,String password)
    {
        return adminService.login(id, password);
    }


    @ApiOperation("获得全部举报信息")
    @RequestMapping(value = "/getReports",method = RequestMethod.GET)
    public AjaxJson getReports()
    {
        List<Report> reportList=adminService.getReports();
        if(reportList.size()==0)
            return new AjaxJson(500,"数据库不存在有关信息",null,0L);
        else
            return new AjaxJson(200,"查询成功",reportList,(long)reportList.size());
    }

    @ApiOperation("管理员处理举报信息时改变账户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID",value = "被举报者ID"),
            @ApiImplicitParam(name = "flag",value = "要改变的状态")
    })
    @RequestMapping(value = "/updateIUserStatus",method = RequestMethod.POST)
    public AjaxJson updateIUserStatus(String ID,int flag)
    {
        int r=adminService.updateIUserStatus(ID, flag);
        if(r==0)
            return new AjaxJson(500,"修改失败",r,0L);
        else
            return new AjaxJson(200,"修改成功",r,1L);
    }

    @ApiOperation("获得全部组织申请信息")
    @RequestMapping(value = "/getApplications",method = RequestMethod.GET)
    public AjaxJson getApplications()
    {
        List<Organization> organizations=adminService.getApplications();
        if(organizations.size()==0)
            return new AjaxJson(200,"数据库不存在有关信息",organizations,0L);
        else
            return new AjaxJson(200,"查询成功",organizations,(long)organizations.size());
    }

    @ApiOperation("管理员处理组织申请信息时改变账户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID",value = "申请者ID"),
            @ApiImplicitParam(name = "flag",value = "要改变的状态")
    })
    @RequestMapping(value = "/updateOUserStatus",method = RequestMethod.POST)
    public AjaxJson updateOUserStatus(int ID,int flag)
    {
        int r=adminService.updateOUserStatus(ID, flag);
        if(r==0)
            return new AjaxJson(200,"修改失败",r,0L);
        else
            return new AjaxJson(200,"修改成功",r,1L);
    }

    @ApiOperation("根据组织ID获得组织邮箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID",value = "组织ID")
    })
    @RequestMapping(value = "/getEmailByOID",method = RequestMethod.GET)
    public AjaxJson getEmailByOID(int ID)
    {
        String email=adminService.getEmailByOID(ID);
        if(email==null)
            return new AjaxJson(200,"查找失败",email,0L);
        else
            return new AjaxJson(200,"查找成功",email,1L);
    }

    @ApiOperation("处理结果发送邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "to",value="发送目标"),
            @ApiImplicitParam(name = "title",value = "邮件主题"),
            @ApiImplicitParam(name = "content",value = "内容")
    })

    @RequestMapping(name = "/sendEmailOFresult",method = RequestMethod.POST)
    public AjaxJson sendEmailOFresult(String to,String title,String content)
    {
        sendEmailUtil.sendHtmlMail(to,title,content);
        return new AjaxJson(200,"发送成功",null,1L);
    }



}
