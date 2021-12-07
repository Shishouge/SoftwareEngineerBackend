package com.example.backend.Service.Account;

import com.example.backend.DAO.Account.IndividualUserMapper;
import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Account.User;
import com.example.backend.Util.Token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import javax.mail.internet.MimeMessage;

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
        IndividualUser individualUser= individualUserMapper.getByUserEmailAndPassword("2872529770@qq.com","111");
        if(individualUser==null)
            return null;
        return individualUser;
    }

    @Override
    public boolean getByEmail(String email)
    {
        IndividualUser individualUser=individualUserMapper.getByEmail(email);
        if(individualUser==null)
            return false;
        else
            return true;
    }

    @Override
    public int insertUser(String email,String password,String userName)
    {
        return individualUserMapper.insertUser(email,password,userName);
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
        message.setFrom("2872529770@qq.com");
        try {
            mailSender.send(message);
            //logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            //logger.error("发送简单邮件时发生异常！", e);
        }
    }

}
