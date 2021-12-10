package com.example.backend.Entity.Discuss;

import lombok.Data;

@Data
public class QuestionWithFollowNumAndLikeNum {

    private int id;
    private String title;
    private String description;
    private int followNum;
    private int answerNum;

    QuestionWithFollowNumAndLikeNum(int id,String title,String description,int followNum,int answerNum)
    {
        this.id = id;
        this.title=title;
        this.description = description;
        this.followNum = followNum;
        this.answerNum = answerNum;
    }
}
