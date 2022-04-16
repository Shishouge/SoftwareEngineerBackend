//test github
package com.example.backend.DAO.Discuss;


import com.example.backend.Entity.Discuss.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscussMapper {

    public List<QuestionWithFollowNumAndLikeNum> getQuestionWithFollowNumAndLikeNum();

    public List<AnswerWithUserInfo> getAllAnswerByQuestionId(int questionId);

    public List<QuestionWithFollowNumAndLikeNumAndAvatar> getQuestionWithFollowNumAndLikeNumAndAvatar();

    public List<QuestionWithFollowNumAndLikeNum> checkFocusQuestion(String userId, int questionId);
    public int addFocusQuestion(String userId, int questionId);
    public int deleteFocusQuestion(String userId, int questionId);

    public List<Comment> getAllCommentByAnswerId(int answerId);

    public AnswerWithInfoAndComment getQuestionByQuestionIdAndAnswerId(int questionId, int answerId);

    public int addQuestion(String userId, String title, String content);

    public int deleteQuestion(int questionId);

   public Question getQuestionById(int questionId);

    public int updateQuestion(int questionId,String title,String content);

    public int addAnswer(String userId, int questionId, String content, String time);

    public int deleteAnswer(int answerId);

    public int updateAnswer(int answerId, String content, String time);

    public int addComment(String userId, int answerId, String content);

    public int deleteComment(String userId, int answerId);

    public int updateComment(String userId, int answerId, String content);

    public int giveLikeOrDislike(String userId, int answerId, int state);

    public int deleteLikeOrDislike(String userId, int answerId);
}
