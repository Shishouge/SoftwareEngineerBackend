package com.example.backend.Controller.Account;

//import com.example.backend.DAO.Account.IndividualUserMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.backend.DAO.Account.OrganizationMapper;
import com.example.backend.Entity.Account.Organization;
//import com.example.backend.Service.Account.IndividualUserServiceImpl;
import com.example.backend.Service.Account.OrganizationServiceImpl;
import com.example.backend.Util.Response.AjaxJson;
import com.example.backend.Util.testcase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
//import org.python.antlr.op.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class OrganizationControllerTest {

    //需要mock的服务
    //@Autowired
    @Mock
    private OrganizationMapper organizationMapper;
    @Mock
    private OrganizationServiceImpl organizationService;
    //上面mock的数据需要注入到哪里
    //@Autowired
    @InjectMocks
    private OrganizationController organizationController;
    @Autowired

    @Before
    public void initMock() {
        MockitoAnnotations.openMocks(this);
    }

    public static String ReadFile(String Path){
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }




    @Test
    void login() {


        //输入mock
        List<Integer> IDs=new ArrayList<>();
        IDs.add(78355314);
        IDs.add(14022057);
        IDs.add(89337568);
        IDs.add(11111111);
        IDs.add(79355314);
        IDs.add(0);
        List<String> passwords=new ArrayList<>();
        passwords.add("12345678A");
        passwords.add("12345678");
        passwords.add("12345678");
        passwords.add("12345678");
        passwords.add("12345678");
        passwords.add("");
        //stub mock
        //接口1模拟
        List<Organization> organizations=new ArrayList<>();
        organizations.add(new Organization(78355314,"tc1","我是用例1","http://101.132.138.14/files/user/66.jpg",1));
        organizations.add(new Organization(14022057,"tc2","我是用例1","http://101.132.138.14/files/user/66.jpg",0));
        organizations.add(new Organization(89337568,"tc3","我是用例1","http://101.132.138.14/files/user/66.jpg",-1));
        organizations.add(null);
        organizations.add(new Organization(78355314,"tc5","我是用例1","http://101.132.138.14/files/user/66.jpg",1));
        organizations.add(null);

        //接口2模拟
        List<Organization> logins=new ArrayList<>();
        logins.add(null);//密码错误
        logins.add(new Organization(14022057,"tc2","我是用例1","http://101.132.138.14/files/user/66.jpg",0));
        logins.add(new Organization(89337568,"tc3","我是用例1","http://101.132.138.14/files/user/66.jpg",-1));
        logins.add(null);
        //登录成功
        logins.add(new Organization(78355314,"tc5","我是用例1","http://101.132.138.14/files/user/66.jpg",1));
        logins.add(null);

        List<String> expectedResults=new ArrayList<>();
        expectedResults.add("密码错误");
        expectedResults.add("该组织还未通过审核");
        expectedResults.add("该组织申请被拒绝");
        expectedResults.add("该组织还未注册");
        expectedResults.add("登录成功");
        expectedResults.add("输入信息不能为空");

        for(int i=0;i<IDs.size();i++)
        {
            //接口1
            Mockito.when(organizationMapper.getOrgByID(Mockito.eq(IDs.get(i)))).thenReturn(organizations.get(i));
            Mockito.when(organizationService.getOrgByID(Mockito.eq(IDs.get(i)))).thenReturn(organizations.get(i));
            //接口2
            Mockito.when(organizationMapper.getByUserAndPassword(Mockito.eq(IDs.get(i)),Mockito.eq(passwords.get(i)))).thenReturn(logins.get(i));
            Mockito.when(organizationService.getByUserAndPassword(Mockito.eq(IDs.get(i)),Mockito.eq(passwords.get(i)))).thenReturn(logins.get(i));

            AjaxJson ajaxJson=organizationController.login(IDs.get(i),passwords.get(i));
            Assert.assertEquals("用例失败",expectedResults.get(i), ajaxJson.getMsg());
            System.out.println(ajaxJson);
        }

    }
}