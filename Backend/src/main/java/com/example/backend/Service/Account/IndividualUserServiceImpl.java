package com.example.backend.Service.Account;

import com.example.backend.DAO.Account.IndividualUserMapper;
import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Account.User;
import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Discuss.Question;
import com.example.backend.Entity.Discuss.QuestionHelper;
import com.example.backend.Util.Token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import javax.mail.internet.MimeMessage;

import java.util.List;
import  java.util.Properties;
import  org.springframework.mail.SimpleMailMessage;
import  org.springframework.mail.javamail.JavaMailSenderImpl;


@Service
public class IndividualUserServiceImpl implements IndividualUserService{
    @Autowired
    IndividualUserMapper individualUserMapper;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    JavaMailSenderImpl mailSender;
    @Override
    public IndividualUser loginByEmail(String ID,String password)
    {
        IndividualUser individualUser= individualUserMapper.getByUserEmailAndPassword(ID, password);
        if(individualUser==null)
            return null;
        return individualUser;
    }

    @Override
    public IndividualUser getByEmail(String email)
    {
        IndividualUser individualUser=individualUserMapper.getByEmail(email);
        return individualUser;
    }

    @Override
    public int insertUser(String email,String password,String userName)
    {
        return individualUserMapper.insertUser(email,password,userName,1);
    }

    @Override
    public int editInformation(String email,String password,String userName,String introduction,String avator)
    {
        return individualUserMapper.editInformation(email,userName,password,introduction,avator);
    }
//    @Override
//    public String loginCheck(String ID,String password, HttpServletResponse response) {
//        User user2 = individualUserMapper.getByUserEmail(ID);
//        if (user2 == null) {
//            return "该用户不存在";
//        }
//        if (!user2.getPASSWORD().equals(password)) {
//            return "密码错误！";
//        }
//        String token = tokenUtil.generateToken(user2);
//        Cookie cookie = new Cookie("token", token);
////        设置cookie的作用域：为”/“时，以在webapp文件夹下的所有应用共享cookie
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        return "登录成功！";
//    }

    @Override
    public void sendEmail(String to,String verifyCode)
    {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("邮箱验证");
        message.setText("您的验证码为："+verifyCode);
        message.setTo(to);
        message.setFrom("drt_test@163.com");
        try {
            mailSender.send(message);
            //logger.info("简单邮件已经发送。");
            System.out.println("以发送");
        } catch (Exception e) {
            //logger.error("发送简单邮件时发生异常！", e);
            System.out.println(e);
        }
    }

    @Override
    public List<Question> getMyQuestions(String ID)
    {
        List<Question> questions=individualUserMapper.getMyQuestions(ID);
        return questions;
    }

    @Override
    public List<QuestionHelper> getMyFocusQuestion(String ID)
    {
        List<QuestionHelper> questionList=individualUserMapper.getMyFocusQuestions(ID);
        return questionList;
    }

    @Override
    public List<Activity> getMyActivities(String ID)
    {
        List<Activity> activities=individualUserMapper.getMySignUpActivities(ID);
        return activities;
    }

    @Override
    public int reportUser(String wID,String rID,String reason,String qID,String aID)
    {
        return individualUserMapper.reportUser(wID, rID, reason,qID,aID);
    }

    @Override
    public int editPassword(String ID,String password)
    {
        return individualUserMapper.editPassword(ID,password);
    }
}
