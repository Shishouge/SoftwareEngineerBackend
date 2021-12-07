package com.example.backend.Service.Account;

import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Account.User;

import javax.servlet.http.HttpServletResponse;

public interface IndividualUserService {
    public IndividualUser loginByEmail(String ID,String password);
    public boolean getByEmail(String email);
    public int insertUser(String email,String password,String userName);
//    public String loginCheck(String ID,String password, HttpServletResponse response);
    public void sendEmail(String to,String verifyCode);
}
