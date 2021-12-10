package com.example.backend.Entity.Discuss;

import lombok.Data;

@Data
public class QuestionWithFollowNumAndLikeNumAndAvatar {

    private int id;
    private String title;
    private String description;
    private int followNum;
    private int answerNum;
    private String avatar;
}
