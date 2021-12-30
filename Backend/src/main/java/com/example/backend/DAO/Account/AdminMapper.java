package com.example.backend.DAO.Account;

import com.example.backend.Entity.Account.Admin;
import com.example.backend.Entity.Account.Application;
import com.example.backend.Entity.Account.Organization;
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
    public int updateIUserStatus(String ID,int flag);
    //获取组织申请
    public List<Organization> getApplications();
    //改变组织用户账号状态
    public int updateOUserStatus(int ID,int flag);
    //根据组织用户ID获取其邮箱
    public String getEmailByOID(int ID);
}
