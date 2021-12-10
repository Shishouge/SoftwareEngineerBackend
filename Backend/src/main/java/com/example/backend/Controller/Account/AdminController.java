package com.example.backend.Controller.Account;

import com.example.backend.Entity.Account.Admin;
import com.example.backend.Entity.Account.Application;
import com.example.backend.Entity.Discuss.Report;
import com.example.backend.Service.Account.AdminService;
import com.example.backend.Util.Response.AjaxJson;
import com.example.backend.Util.Response.ResponseCode;
import com.example.backend.Util.Response.ResponseResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
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
    public AjaxJson updateIUserStatus(String ID,boolean flag)
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
        List<Application> applications=adminService.getApplications();
        if(applications.size()==0)
            return new AjaxJson(500,"数据库不存在有关信息",null,0L);
        else
            return new AjaxJson(200,"查询成功",applications,(long)applications.size());
    }

    @ApiOperation("管理员处理组织申请信息时改变账户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID",value = "申请者ID"),
            @ApiImplicitParam(name = "flag",value = "要改变的状态")
    })
    @RequestMapping(value = "/updateOUserStatus",method = RequestMethod.POST)
    public AjaxJson updateOUserStatus(int ID,boolean flag)
    {
        int r=adminService.updateOUserStatus(ID, flag);
        if(r==0)
            return new AjaxJson(500,"修改失败",r,0L);
        else
            return new AjaxJson(200,"修改成功",r,1L);
    }


}
