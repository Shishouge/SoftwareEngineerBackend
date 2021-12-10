package com.example.backend.DAO.Account;

import com.example.backend.Entity.Account.Organization;
import com.example.backend.Entity.Activity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper {
    //注册
    int insertOrganization(String userName,String password,String introduction,String avator,String certification,int status);
    Organization getOrgByID(int ID);
    Organization getOrgByName(String name);
    //登录
    Organization getByUserAndPassword(int ID,String password);
    //修改个人信息
    int editInformation(String ID,String userName,String password,String introduction,String avator );
    //查看该组织当前状态
    int getStatus(String userName);
    //获得某组织发布的活动
    List<Activity> getActivityByOrgID(int ID);

}
