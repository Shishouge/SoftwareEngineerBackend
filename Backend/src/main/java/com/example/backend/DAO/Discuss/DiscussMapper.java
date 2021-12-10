package com.example.backend.DAO.Discuss;


import com.example.backend.Entity.Discuss.QuestionWithFollowNumAndLikeNum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscussMapper {

    public List<QuestionWithFollowNumAndLikeNum> getQuestionWithFollowNumAndLikeNum();
}
