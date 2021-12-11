package com.example.backend.Entity.Discuss;

import lombok.Data;

import java.util.List;

@Data
public class AnswerWithInfoAndComment {
    private  String answer;
    private  String question;
    private  String time;
    private  Info info;
    private List<Comment> comment;
}
