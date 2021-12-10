package com.example.backend.Service.Discuss;

import com.example.backend.DAO.Discuss.DiscussMapper;
import com.example.backend.Entity.Discuss.AnswerWithUserInfo;
import com.example.backend.Entity.Discuss.QuestionWithFollowNumAndLikeNum;
import com.example.backend.Entity.Discuss.QuestionWithFollowNumAndLikeNumAndAvatar;
import com.example.backend.Util.Response.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussServiceImpl implements DiscussService {

    private QuestionWithFollowNumAndLikeNum questionWithFollowNumAndLikeNum;

    @Autowired
    private DiscussMapper discussMapper;

    @Override
    public AjaxJson getQuestionWithFollowNumAndLikeNum()
    {
        List<QuestionWithFollowNumAndLikeNum> list = discussMapper.getQuestionWithFollowNumAndLikeNum();
        if(list.isEmpty())
        {
            throw  new IllegalStateException("问题列表为空");
        }
        return new AjaxJson(200,"返回成功",list);
    }

    @Override
    public AjaxJson getAllAnswerByQuestionId(int questionId)
    {
        List<AnswerWithUserInfo> list = discussMapper.getAllAnswerByQuestionId(questionId);
        if(list.isEmpty())
        {
            throw  new IllegalStateException("回答列表为空");
        }
        return new AjaxJson(200,"返回成功",list);
    }

    @Override
    public AjaxJson getQuestionWithFollowNumAndLikeNumAndAvatar()
    {
        List<QuestionWithFollowNumAndLikeNumAndAvatar> list = discussMapper.getQuestionWithFollowNumAndLikeNumAndAvatar();
        if(list.isEmpty())
        {
            throw  new IllegalStateException("问题列表为空");
        }
        return new AjaxJson(200,"返回成功",list);
    }
}
