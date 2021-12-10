package com.example.backend.Entity.Discuss;

import lombok.Data;

@Data
public class AnswerWithUserInfo {
    private  int id;
    private String content;
    private int approveNum;
    private int disapproveNum;
    private UserInfo userInfo;
}
