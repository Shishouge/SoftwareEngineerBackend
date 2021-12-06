package com.example.backend.Service.Account;

import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Account.User;

import javax.servlet.http.HttpServletResponse;

public interface IndividualUserService {
    public boolean loginByEmail(String ID,String password);
    public String loginCheck(String ID,String password, HttpServletResponse response);
}
