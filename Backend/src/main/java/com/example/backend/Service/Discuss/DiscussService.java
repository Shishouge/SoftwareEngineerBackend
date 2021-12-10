package com.example.backend.Service.Discuss;

import com.example.backend.Util.Response.AjaxJson;

public interface DiscussService {

     public AjaxJson getQuestionWithFollowNumAndLikeNum();

     public AjaxJson getAllAnswerByQuestionId(int questionId);

     public AjaxJson getQuestionWithFollowNumAndLikeNumAndAvatar();
}
