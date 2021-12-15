package com.example.backend.Service.Discuss;

import com.example.backend.DAO.Discuss.DiscussMapper;
import com.example.backend.Entity.Discuss.*;
import com.example.backend.Util.Response.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussServiceImpl implements DiscussService {


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

    @Override
    public  AjaxJson checkFocusQuestion(String userId, int questionId)
    {
        List<QuestionWithFollowNumAndLikeNum> list = discussMapper.checkFocusQuestion(userId,questionId);
        if(list.isEmpty())
        {
            return new AjaxJson(200,"该问题未关注",0);
        }
        else {
            return new AjaxJson(200, "该问题已关注", 1);
        }
    }

    @Override
    public  AjaxJson takeAntiFocusQuestion(String userId, int questionId)
    {
        List<QuestionWithFollowNumAndLikeNum> list = discussMapper.checkFocusQuestion(userId,questionId);
        if(list.isEmpty())
        {
            //没有找到，需要增加关注该问题
            if(discussMapper.addFocusQuestion(userId,questionId)==0)
            {
                return new AjaxJson(500, "该问题未新增关注", 1);
            }
            else {
                return new AjaxJson(200, "该问题已新增关注", 1);
            }
        }
        else {
            //找到，需要删除关注该问题
            if(discussMapper.deleteFocusQuestion(userId,questionId) == 0)
            {
                return new AjaxJson(500, "该问题未取消关注", 0);
            }
            else {
                return new AjaxJson(200, "该问题已取消关注", 0);
            }
        }
    }

    @Override
    public AjaxJson getAllCommentByQuestionIdAndAnswerId(int questionId, int answerId)
    {
        List<Comment> list = discussMapper.getAllCommentByAnswerId(answerId);
        AnswerWithInfoAndComment answerWithInfoAndComment =
                discussMapper.getQuestionByQuestionIdAndAnswerId(questionId,answerId);
        answerWithInfoAndComment.setComment(list);
        if(list.isEmpty())
        {
            return new AjaxJson(200,"该问题没有评论",answerWithInfoAndComment);
        }
        else {
            return new AjaxJson(200, "该问题有评论", answerWithInfoAndComment);
        }
    }

    @Override
    public AjaxJson addQuestion(String userId, String title, String content)
    {
        if(discussMapper.addQuestion(userId,title,content)==0)
        {
            throw  new IllegalStateException("未插入成功");
        }
        else
        {
            return new AjaxJson(200,"插入成功",1);
        }
    }

    @Override
    public AjaxJson deleteQuestion(int questionId)
    {
        if(discussMapper.deleteQuestion(questionId)==0)
        {
            throw  new IllegalStateException("未删除成功");
        }
        else
        {
            return new AjaxJson(200,"删除成功",1);
        }
    }

    @Override
    public AjaxJson getQuestionById(int questionId)
    {
        Question question=discussMapper.getQuestionById(questionId);
        if(question!=null)
        {
            return new AjaxJson(200,"该问题存在",question,1l);
        }
        else
        {
            return new AjaxJson(500,"该问题不存在",question);
        }
    }

    @Override
    public AjaxJson updateQuestion(int questionId,String title, String content)
    {
        if(discussMapper.updateQuestion(questionId,title,content) == 0)
        {
            throw  new IllegalStateException("该问题未更新");
        }
        else
        {
            return new AjaxJson(200,"该问题已更新成功",1);
        }
    }

    @Override
    public AjaxJson addAnswer(String userId, int questionId, String content, String time)
    {

        if(discussMapper.addAnswer(userId,questionId,content,time)==0)
        {
            throw  new IllegalStateException("未插入成功");
        }
        else
        {
            return new AjaxJson(200,"插入成功",1);
        }
    }

    @Override
    public AjaxJson deleteAnswer(int answerId)
    {
        if(discussMapper.deleteAnswer(answerId)==0)
        {
            throw  new IllegalStateException("未删除成功");
        }
        else
        {
            return new AjaxJson(200,"删除成功",1);
        }
    }

    @Override
    public AjaxJson updateAnswer(int answerId, String content, String time)
    {
        if(discussMapper.updateAnswer(answerId,content,time) == 0)
        {
            throw  new IllegalStateException("该回答未更新");
        }
        else
        {
            return new AjaxJson(200,"该回答已更新成功",1);
        }
    }
        @Override
        public AjaxJson addComment(String userId, int answerId, String content)
        {
            if(discussMapper.addComment(userId,answerId,content)==0)
            {
                throw  new IllegalStateException("未插入成功");
            }
            else
            {
                return new AjaxJson(200,"插入成功",1);
            }
        }

        @Override
        public AjaxJson deleteComment(String userId, int answerId)
        {
            if(discussMapper.deleteComment(userId,answerId)==0)
            {
                throw  new IllegalStateException("未删除成功");
            }
            else
            {
                return new AjaxJson(200,"删除成功",1);
            }
        }
        @Override
        public AjaxJson updateComment(String userId, int answerId, String content)
        {
            if(discussMapper.updateComment(userId,answerId,content) == 0)
            {
                throw  new IllegalStateException("该评论未更新");
            }
            else
            {
                return new AjaxJson(200,"该评论已更新成功",1);
            }
        }
        @Override
        public AjaxJson giveLikeOrDislike(String userId, int answerId, int state)
        {
            if(discussMapper.giveLikeOrDislike(userId,answerId,state)==0)
            {
                throw  new IllegalStateException("未插入成功");
            }
            else
            {
                return new AjaxJson(200,"插入成功",1);
            }
        }
        @Override
        public AjaxJson deleteLikeOrDislike(String userId, int answerId)
        {
            if(discussMapper.deleteLikeOrDislike(userId,answerId)==0)
            {
                throw  new IllegalStateException("未删除成功");
            }
            else
            {
                return new AjaxJson(200,"删除成功",1);
            }
        }

}
