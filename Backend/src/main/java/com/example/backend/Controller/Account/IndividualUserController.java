package com.example.backend.Controller.Account;

import com.example.backend.DAO.Account.IndividualUserMapper;
import com.example.backend.Entity.Account.User;
import com.example.backend.Service.Account.IndividualUserService;
import com.example.backend.Util.Response.ResponseCode;
import com.example.backend.Util.Response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "IndividualUserSystem")
@RestController
public class IndividualUserController {
    @Autowired
    IndividualUserService individualUserService;
    @ApiOperation("登录接口，传入邮箱和密码，返回true/false")
    @ApiImplicitParams({
            @ApiImplicitParam(name="email",value = "用户邮箱",defaultValue = "2872529770@qq.com",required = true),
            @ApiImplicitParam(name="password",value = "用户密码",defaultValue = "111",required = true)
    })
    @RequestMapping(value="/login",method = {RequestMethod.GET})
    public ResponseResult login(String ID, String password)
    {
        boolean result=individualUserService.loginByEmail("2872529770@qq.com","123");
        String mes=result?"登录成功":"登录失败，查无此人！";
        return ResponseResult.e(result?ResponseCode.OK:ResponseCode.FAIL,mes);
    }


    @ApiOperation("登录检查接口，传入邮箱和密码，返回true/false")
    @ApiImplicitParams({
            @ApiImplicitParam(name="email",value = "用户邮箱",defaultValue = "2872529770@qq.com",required = true),
            @ApiImplicitParam(name="password",value = "用户密码",defaultValue = "111",required = true)
    })
    @RequestMapping(value = "/loginCheck",method = {RequestMethod.GET})
    public ResponseResult loginCheck( String ID, String password, HttpServletResponse response)
    {
        ID="2872529770@qq.com";
        password="123";
        String result = individualUserService.loginCheck(ID,password,response);
        return ResponseResult.e(result.equals("登录成功！")?ResponseCode.OK:ResponseCode.FAIL,result);
    }

    @ApiOperation("检查接口")
    @RequestMapping(value = "/check",method = {RequestMethod.GET})
    public String check()
    {
        return "yes";
    }

}
