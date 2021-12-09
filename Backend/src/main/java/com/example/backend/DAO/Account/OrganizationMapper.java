package com.example.backend.DAO.Account;

import com.example.backend.Entity.Account.Organization;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrganizationMapper {
    //注册
    int insertOrganization(String userName,String password,String introduction,String avator,String certification,int status);
    Organization getOrgByID(int ID);
    Organization getOrgByName(String name);
    //登录
    Organization getByUserAndPassword(String userName,String password);
    //修改个人信息
    int editInformation(String ID,String userName,String password,String introduction,String avator );
    //查看该组织当前状态
    int getStatus(String userName);

}
