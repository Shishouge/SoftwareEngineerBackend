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
}
