package com.example.backend.Service.Account;

import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Account.User;
import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Discuss.Question;
import com.example.backend.Entity.Discuss.QuestionHelper;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IndividualUserService {
    //登录
    public IndividualUser loginByEmail(String ID,String password);
    //注册时检查该账号之前是否注册过
    public IndividualUser getByEmail(String email);
    //注册插入用户
    public int insertUser(String email,String password,String userName);
    //更改个人信息
    public int editInformation(String email,String password,String userName,String introduction,String avator);
//    public String loginCheck(String ID,String password, HttpServletResponse response);
    public void sendEmail(String to,String verifyCode);
    //返回某人发布的问题
    public List<Question> getMyQuestions(String ID);
    //返回某人关注的问题
    public List<QuestionHelper> getMyFocusQuestion(String ID);
    //返回某人报名了的活动
    public List<Activity> getMyActivities(String id);
    //举报用户
    public int reportUser(String wID,String rID,String reason);
}
