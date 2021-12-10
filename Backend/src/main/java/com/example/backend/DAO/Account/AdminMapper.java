package com.example.backend.DAO.Account;

import com.example.backend.Entity.Account.Admin;
import com.example.backend.Entity.Account.Application;
import com.example.backend.Entity.Discuss.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {
    //登录
    public Admin getByIDandPassword(int ID,String password);
    //获取举报信息
    public List<Report> getReports();
    //改变个人用户账号状态
    public int updateIUserStatus(String ID,boolean flag);
    //获取组织申请
    public List<Application> getApplications();
    //改变组织用户账号状态
    public int updateOUserStatus(int ID,boolean flag);
}
