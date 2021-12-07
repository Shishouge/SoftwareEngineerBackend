package com.example.backend.DAO.Account;

import com.example.backend.Entity.Account.IndividualUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IndividualUserMapper {
    //通过邮箱和密码判断信息是否匹配
    IndividualUser getByUserEmailAndPassword(String ID, String password);
    IndividualUser getByEmail(String ID);
    int insertUser(String ID,String password,String userName);

}
