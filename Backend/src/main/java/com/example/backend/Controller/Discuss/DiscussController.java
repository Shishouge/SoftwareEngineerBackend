package com.example.backend.Controller.Discuss;


import com.example.backend.Service.Discuss.DiscussService;
import com.example.backend.Util.Response.AjaxJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags="讨论控制板块")
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
}
