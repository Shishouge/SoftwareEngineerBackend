package com.example.backend.Controller.Account;

import com.example.backend.DAO.Account.IndividualUserMapper;
import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Service.Account.IndividualUserService;
import com.example.backend.Service.Account.IndividualUserServiceImpl;
import com.example.backend.Util.Response.AjaxJson;
import jnr.ffi.annotations.In;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class IndividualUserControllerTest {

    //需要mock的服务
    //@Autowired
    @Mock
    private IndividualUserMapper individualUserMapper;
    @Mock
    private IndividualUserServiceImpl individualUserService;
    //上面mock的数据需要注入到哪里
    //@Autowired
    @InjectMocks
    private IndividualUserController individualUserController;
    @Autowired

    @Before
    public void initMock() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void login() {
        //模拟stub
        List<IndividualUser> users=new ArrayList<>();
        users.add(new IndividualUser("DRT@qq.com","小邓","测试用户1","http://101.132.138.14/files/user/66.jpg",1));
        users.add(new IndividualUser("WHL@qq.com","小王","测试用户2","http://101.132.138.14/files/user/66.jpg",0));
        users.add(null);
        users.add(new IndividualUser("SSG@qq.com","小时","测试用户4","http://101.132.138.14/files/user/66.jpg",1));


        List<IndividualUser> loginusers=new ArrayList<>();
        loginusers.add(null);
        loginusers.add(new IndividualUser("WHL@qq.com","小王","测试用户2","http://101.132.138.14/files/user/66.jpg",0));
        loginusers.add(null);
        loginusers.add(new IndividualUser("SSG@qq.com","小时","测试用户4","http://101.132.138.14/files/user/66.jpg",1));


        //参数mock
        List<String> emails=new ArrayList<>();
        emails.add("DRT@qq.com");
        emails.add("WHL@qq.com");
        emails.add("ABC@qq.com");
        emails.add("SSG@qq.com");

        List<String> passwords=new ArrayList<>();
        passwords.add("12345678A");
        passwords.add("12345678");
        passwords.add("12345678");
        passwords.add("12345678");

        List<String> expectedResults=new ArrayList<>();
        expectedResults.add("密码错误");
        expectedResults.add("该用户目前处于封禁状态");
        expectedResults.add("该用户尚未注册");
        expectedResults.add("登录成功");

        for(int i=0;i<users.size();i++)
        {
            Mockito.when(individualUserMapper.getByEmail(Mockito.eq(emails.get(i)))).thenReturn(users.get(i));
            Mockito.when(individualUserService.getByEmail(Mockito.eq(emails.get(i)))).thenReturn(users.get(i));
            Mockito.when(individualUserMapper.getByUserEmailAndPassword(Mockito.eq(emails.get(i)),Mockito.eq(passwords.get(i)))).thenReturn(loginusers.get(i));
            Mockito.when(individualUserService.loginByEmail(Mockito.eq(emails.get(i)),Mockito.eq(passwords.get(i)))).thenReturn(loginusers.get(i));

            AjaxJson ajaxJson=individualUserController.login(emails.get(i),passwords.get(i));
            //断言查看是否正确
            Assert.assertEquals(expectedResults.get(i), ajaxJson.getMsg());
            //System.out.println(ajaxJson);
        }
    }

    @Test
    void signUp() {
        //输入参数Mock
        List<String> emails=new ArrayList<>();
        emails.add("DRT@qq.com");
        emails.add("2872521111@qq.com");
        List<String> passwords=new ArrayList<>();
        passwords.add("12345678As");
        passwords.add("987654wq");
        List<String> names=new ArrayList<>();
        names.add("小蓝");
        names.add("小红");

        //stub
        List<IndividualUser> users=new ArrayList<>();
        users.add(new IndividualUser("DRT@qq.com","小邓","测试用户1","http://101.132.138.14/files/user/66.jpg",1));
        users.add(null);
        int x=1;
        List<String> expectedResults=new ArrayList<>();
        expectedResults.add("该账号已被注册");
        expectedResults.add("注册成功");
        for(int i=0;i< emails.size();i++)
        {
            Mockito.when(individualUserMapper.getByEmail(Mockito.eq(emails.get(i)))).thenReturn(users.get(i));
            Mockito.when(individualUserService.getByEmail(Mockito.eq(emails.get(i)))).thenReturn(users.get(i));

            Mockito.when(individualUserService.insertUser(Mockito.eq(emails.get(i)),Mockito.eq(passwords.get(i)),Mockito.eq(names.get(i)))).thenReturn(x);
            Mockito.when(individualUserMapper.insertUser(Mockito.eq(emails.get(i)),Mockito.eq(passwords.get(i)),Mockito.eq(names.get(i)),Mockito.eq(1))).thenReturn(x);
            AjaxJson ajaxJson=individualUserController.signUp(emails.get(i),names.get(i), passwords.get(i));
            Assert.assertEquals(expectedResults.get(i), ajaxJson.getMsg());
            //System.out.println(ajaxJson);
        }



    }
}