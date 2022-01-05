package com.example.backend.DAO.Account;

import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Discuss.Question;
import com.example.backend.Entity.Discuss.QuestionHelper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndividualUserMapper {
    //通过邮箱和密码判断信息是否匹配
    IndividualUser getByUserEmailAndPassword(String ID, String password);
    //注册时看该邮箱之前是否注册过
    IndividualUser getByEmail(String ID);
    //注册时插入用户
    int insertUser(String ID,String password,String userName,int status);
    //更新用户信息
    int editInformation(String email,String name,String password,String introduction,String avator );
    //返回该用户发布过的问题
    List<Question> getMyQuestions(String ID);
    //返回该用户关注的问题
    List<QuestionHelper> getMyFocusQuestions(String ID);
    //返回该用户已报名的活动
    List<Activity> getMySignUpActivities(String ID);
    //举报用户
    int reportUser(String wID,String rID,String reason,String qID,String aID);
    //加密密码
    int encode();
    //修改密码
    int editPassword(String ID,String password);


}
