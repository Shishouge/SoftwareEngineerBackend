package com.example.backend.Service.Account;

import com.example.backend.DAO.Account.IndividualUserMapper;
import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Account.User;
import com.example.backend.Util.Token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Service
public class IndividualUserServiceImpl implements IndividualUserService{
    @Autowired
    IndividualUserMapper individualUserMapper;
    @Autowired
    TokenUtil tokenUtil;
    @Override
    public boolean loginByEmail(String ID,String password)
    {
        User individualUser= individualUserMapper.getByUserEmailAndPassword("2872529770@qq.com","111");
        if(individualUser==null)
            return false;
        return true;
    }

    @Override
    public String loginCheck(String ID,String password, HttpServletResponse response) {
        User user2 = individualUserMapper.getByUserEmail(ID);
        if (user2 == null) {
            return "该用户不存在";
        }
        if (!user2.getPASSWORD().equals(password)) {
            return "密码错误！";
        }
        String token = tokenUtil.generateToken(user2);
        Cookie cookie = new Cookie("token", token);
//        设置cookie的作用域：为”/“时，以在webapp文件夹下的所有应用共享cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        return "登录成功！";
    }

}
