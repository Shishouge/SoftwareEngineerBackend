package com.example.backend.Controller.Discuss;


import com.example.backend.Service.Discuss.DiscussService;
import com.example.backend.Util.Response.AjaxJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@Api(tags="DiscussController")
@RequestMapping("/discuss")
public class DiscussController {

    @Autowired
    private DiscussService discussService;

    @ApiOperation("获得所有的问题")
    @GetMapping("/getQuestionWithFollowNumAndLikeNum")
    public AjaxJson getQuestionWithFollowNumAndLikeNum()
    {
        return discussService.getQuestionWithFollowNumAndLikeNum();
    }

    @ApiOperation("获得所有的精选问题")
    @GetMapping("/getQuestionWithFollowNumAndLikeNumAndAvatar")
    public AjaxJson getQuestionWithFollowNumAndLikeNumAndAvatar()
    {
        return discussService.getQuestionWithFollowNumAndLikeNumAndAvatar();
    }


    @ApiOperation("通过问题的id获得所有的回答")
    @PostMapping("getAllAnswerByQuestionId")
    public AjaxJson getAllAnswerByQuestionId(int questionId)
    {
        return discussService.getAllAnswerByQuestionId(questionId);
    }

    @ApiOperation("查看是否关注该问题")
    @GetMapping("checkFocusQuestion")
    public  AjaxJson checkFocusQuestion(String userId,int questionId)
    {
        return discussService.checkFocusQuestion(userId,questionId);
    }

    @ApiOperation("对关注问题取反")
    @PostMapping("takeAntiFocusQuestion")
    public  AjaxJson takeAntiFocusQuestion(String userId,int questionId)
    {
        return discussService.takeAntiFocusQuestion(userId,questionId);
    }

    @ApiOperation("根据问题的ID和回答的ID得到所有的评论")
    @GetMapping("getAllCommentByQuestionIdAndAnswerId")
    public AjaxJson getAllCommentByQuestionIdAndAnswerId(int questionId,int answerId)
    {
        return discussService.getAllCommentByQuestionIdAndAnswerId(questionId,answerId);
    }

    @ApiOperation("根据问题id得到问题的详细信息")
    @GetMapping("getQuestionById")
    public AjaxJson getQuestionById(int questionId)
    {
        return discussService.getQuestionById(questionId);
    }


    @ApiOperation("发布问题")
    @PostMapping("addQuestion")
    public AjaxJson publishQuestion(String userId,String title,String content)
    {
        return discussService.addQuestion(userId,title,content);
    }

    @ApiOperation("删除问题")
    @PostMapping("deleteQuestion")
    public AjaxJson deleteQuestion(int questionId)
    {
        return discussService.deleteQuestion(questionId);
    }

    @ApiOperation("更新问题")
    @PostMapping("updateQuestion")
    public AjaxJson updateQuestion(int questionId,String title,String content)
    {
        return discussService.updateQuestion(questionId,title,content);
    }

    @ApiOperation("发布回答")
    @PostMapping("/addAnswer")
    public AjaxJson addAnswer(String userId,int questionId,String content)
    {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        String time = date.toString();
        return discussService.addAnswer(userId,questionId,content,time);
    }

    @ApiOperation("删除回答")
    @PostMapping("deleteAnswer")
    public AjaxJson deleteAnswer(int answerId)
    {
        return discussService.deleteAnswer(answerId);
    }

    @ApiOperation("更新回答")
    @PostMapping("updateAnswer")
    public AjaxJson updateAnswer(int answerId,String content)
    {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        String time = date.toString();
        return discussService.updateAnswer(answerId,content,time);
    }

    @ApiOperation("发布评论")
    @PostMapping("addComment")
    public AjaxJson addComment(String userId,int answerId,String content)
    {
        return discussService.addComment(userId,answerId,content);
    }

    @ApiOperation("删除评论")
    @PostMapping("deleteComment")
    public AjaxJson deleteComment(String userId,int answerId)
    {
        return discussService.deleteComment(userId,answerId);
    }

    @ApiOperation("更新评论")
    @PostMapping("updateComment")
    public AjaxJson updateComment(String userId,int answerId,String content)
    {
        return discussService.updateComment(userId,answerId,content);
    }

    @ApiOperation("点赞/点踩")
    @PostMapping("giveLikeOrDislike")
    public AjaxJson giveLikeOrDislike(String userId,int answerId,int state)
    {
        return discussService.giveLikeOrDislike(userId,answerId,state);
    }

    @ApiOperation("取消点赞/点踩")
    @PostMapping("deleteLikeOrDislike")
    public AjaxJson deleteLikeOrDislike(String userId,int answerId)
    {
        return discussService.deleteLikeOrDislike(userId,answerId);
    }
}
