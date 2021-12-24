package com.example.backend.Controller.Test;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.Util.Email.VerifyEmailUtil;
import com.example.backend.Util.Response.AjaxJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@Api(tags="测试")
@RequestMapping("/test")
public class TestController {
    @Autowired
    VerifyEmailUtil verifyEmailUtil;
    @ApiOperation("登陆（使用的账号是10001）")
    @GetMapping("/doLogin")
    public SaResult doLogin() {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        StpUtil.logout();
        StpUtil.login(10001);
        return SaResult.ok("登录成功");
    }

    @GetMapping("isLogin")
    @ApiOperation("查询登录状态")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @GetMapping("logout")
    @ApiOperation("退出登录(把登陆的10001登出去)")
    public String logout() {
        StpUtil.logoutByLoginId(10001);
        return "SUCCESS";
    }

    @ApiOperation("登陆后校验，能返回证明登陆成功")
    @GetMapping("/doCheck")
    public JSONObject doCheck() {

        JSONObject json = new JSONObject();
        json.put("status", 200);
        json.put("result", "提交成功！");
        return json;
    }

    @PostMapping("/mail")
    @ApiOperation("发送验证码到邮箱")
    public AjaxJson sendVerifyCode(HttpServletRequest request, String email) {
        return verifyEmailUtil.sendMail(request,email);
    }

    @GetMapping("/verify")
    @ApiOperation("检验验证码")
    public AjaxJson verify(String inputCode, HttpServletRequest request){
        return verifyEmailUtil.checkCode(inputCode,request);
    }



}
