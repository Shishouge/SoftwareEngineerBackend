package com.example.backend.Service.Discuss;

import com.example.backend.Util.Response.AjaxJson;

public interface DiscussService {

     public AjaxJson getQuestionWithFollowNumAndLikeNum();

     public AjaxJson getAllAnswerByQuestionId(int questionId);

     public AjaxJson getQuestionWithFollowNumAndLikeNumAndAvatar();

     public AjaxJson checkFocusQuestion(String userId, int questionId);

     public AjaxJson takeAntiFocusQuestion(String userId, int questionId);

     public AjaxJson getAllCommentByQuestionIdAndAnswerId(int questionId, int answerId);
}
