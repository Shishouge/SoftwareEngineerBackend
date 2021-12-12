package com.example.backend.Service.Discuss;

import com.example.backend.Util.Response.AjaxJson;

public interface DiscussService {

     public AjaxJson getQuestionWithFollowNumAndLikeNum();

     public AjaxJson getAllAnswerByQuestionId(int questionId);

     public AjaxJson getQuestionWithFollowNumAndLikeNumAndAvatar();

     public AjaxJson checkFocusQuestion(String userId, int questionId);

     public AjaxJson takeAntiFocusQuestion(String userId, int questionId);

     public AjaxJson getAllCommentByQuestionIdAndAnswerId(int questionId, int answerId);

     public AjaxJson addQuestion(String userId, String title, String content);

     public AjaxJson deleteQuestion(int questionId);

     public AjaxJson getQuestionById(int questionId);

     public AjaxJson updateQuestion(int questionId,String title, String content);

     public AjaxJson addAnswer(String userId, int questionId, String content, String time);

     public AjaxJson deleteAnswer(int answerId);

     public AjaxJson updateAnswer(int answerId, String content, String time);

     public AjaxJson addComment(String userId, int answerId, String content);

     public AjaxJson deleteComment(String userId, int answerId);

     public AjaxJson updateComment(String userId, int answerId, String content);
}
