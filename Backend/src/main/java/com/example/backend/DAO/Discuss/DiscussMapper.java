package com.example.backend.DAO.Discuss;


import com.example.backend.Entity.Discuss.AnswerWithUserInfo;
import com.example.backend.Entity.Discuss.QuestionWithFollowNumAndLikeNum;
import com.example.backend.Entity.Discuss.QuestionWithFollowNumAndLikeNumAndAvatar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscussMapper {

    public List<QuestionWithFollowNumAndLikeNum> getQuestionWithFollowNumAndLikeNum();

    public List<AnswerWithUserInfo> getAllAnswerByQuestionId(int questionId);

    public List<QuestionWithFollowNumAndLikeNumAndAvatar> getQuestionWithFollowNumAndLikeNumAndAvatar();
}
