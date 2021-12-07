package com.example.backend.Controller.Account;

import com.example.backend.DAO.Account.IndividualUserMapper;
import com.example.backend.Entity.Account.IndividualUser;
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
import java.util.Random;

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
        IndividualUser result=individualUserService.loginByEmail("2872529770@qq.com","123");
        return ResponseResult.e(result!=null?ResponseCode.OK:ResponseCode.FAIL,result);
    }

    @ApiOperation("注册接口，传入邮箱，用户名，密码，返回注册信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="email",value="用户邮箱",required = true),
            @ApiImplicitParam(name="name",value="用户名称",required = true),
            @ApiImplicitParam(name="password",value="用户密码",required = true),
    })
    @RequestMapping(value="/signUp",method = {RequestMethod.POST})
    public ResponseResult signUp(String email,String name,String password)
    {
        //返回0表示数据库原因未插入成功
        //返回1表示插入成功
        //返回-1表示该邮箱已经有人注册
        boolean check=individualUserService.getByEmail(email);
        int insert=-2;
        if(check)
        {
            insert=individualUserService.insertUser(email,password,name);
        }
        String result;
        if(check==false&&insert==1) result="插入成功";
        else if(check==true) result="该邮箱已经有人注册";
        else result="数据库原因未插入成功";
        return ResponseResult.e(result.equals("插入成功")?ResponseCode.OK:ResponseCode.FAIL,result);

    }

//    @ApiOperation("登录检查接口，传入邮箱和密码，返回true/false")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="email",value = "用户邮箱",defaultValue = "2872529770@qq.com",required = true),
//            @ApiImplicitParam(name="password",value = "用户密码",defaultValue = "111",required = true)
//    })
//    @RequestMapping(value = "/loginCheck",method = {RequestMethod.GET})
//    public ResponseResult loginCheck( String ID, String password, HttpServletResponse response)
//    {
//        ID="2872529770@qq.com";
//        password="123";
//        String result = individualUserService.loginCheck(ID,password,response);
//        return ResponseResult.e(result.equals("登录成功！")?ResponseCode.OK:ResponseCode.FAIL,result);
//    }

    @ApiOperation("检查接口")
    @RequestMapping(value = "/check",method = {RequestMethod.GET})
    public String check()
    {
        return "yes";
    }

    @ApiOperation("邮箱验证")
    @RequestMapping(value = "/sendEmail",method={RequestMethod.GET})
    public ResponseResult sendEmail(String to)
    {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        try {
            individualUserService.sendEmail("2577672771@qq.com",checkCode);
        }catch (Exception e){
            return ResponseResult.e(ResponseCode.FAIL,checkCode);
        }
        return ResponseResult.e(ResponseCode.OK,checkCode);
    }

}
