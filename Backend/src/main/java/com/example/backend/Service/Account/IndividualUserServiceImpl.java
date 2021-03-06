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
//            return "??????????????????";
//        }
//        if (!user2.getPASSWORD().equals(password)) {
//            return "???????????????";
//        }
//        String token = tokenUtil.generateToken(user2);
//        Cookie cookie = new Cookie("token", token);
////        ??????cookie?????????????????????/???????????????webapp?????????????????????????????????cookie
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        return "???????????????";
//    }

    @Override
    public void sendEmail(String to,String verifyCode)
    {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("????????????");
        message.setText("?????????????????????"+verifyCode);
        message.setTo(to);
        message.setFrom("drt_test@163.com");
        try {
            mailSender.send(message);
            //logger.info("???????????????????????????");
            System.out.println("?????????");
        } catch (Exception e) {
            //logger.error("????????????????????????????????????", e);
            System.out.println("?????????"+e);
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
