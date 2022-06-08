package com.example.backend.Service.Activity;

import com.example.backend.Controller.Account.IndividualUserController;
import com.example.backend.DAO.Account.IndividualUserMapper;
import com.example.backend.DAO.Activity.ActivityMapper;
import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Activity.EmotionAnalysis;
import com.example.backend.Entity.Activity.ReviewActivity;
import com.example.backend.Service.Account.IndividualUserServiceImpl;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ActivityServiceImplTest {
    //需要mock的服务
    //@Autowired
    @Mock
    private ActivityMapper activityMapper;
    //上面mock的数据需要注入到哪里
    //@Autowired
    @InjectMocks
    private ActivityServiceImpl activityService;

    @Autowired
    @Before
    public void initMock() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void runEmotionAnalysis() {
        activityService.runEmotionAnalysis(0);
        activityService.runEmotionAnalysis(1);
        activityService.runEmotionAnalysis(11);
    }

    //运行前要把Python处理部分注释掉 不要轻易运行
    @Test
    void getEmotionalAnalysis() {
        //输入Mock
        List<Integer> IDs=new ArrayList<>();
        IDs.add(1);
        IDs.add(2);
        IDs.add(35);
        //接口1 构造评论使得个数达标
        List<List<ReviewActivity>> reviewActivitiesList=new ArrayList<>();
        for(int i=0;i<3;i++)
        {
            List<ReviewActivity> reviewActivities=new ArrayList<>();
            for(int j=0;j<5*i+5;j++)
            {
                reviewActivities.add(new ReviewActivity("好！"));
            }
            reviewActivitiesList.add(reviewActivities);
        }


        for(int i=0;i<IDs.size();i++)
        {
            Mockito.when(activityMapper.getReviewsByActivity(Mockito.eq(IDs.get(i)))).thenReturn(reviewActivitiesList.get(i));
            Mockito.when(activityMapper.getIMGofActivity(Mockito.eq(IDs.get(i)))).thenReturn(new EmotionAnalysis("来自数据库的缓存","来自数据库的缓存"));
            EmotionAnalysis emotionAnalysis=activityService.getEmotionalAnalysis(IDs.get(i));
            if(emotionAnalysis.getEMO_ANALYSIS().equals(""))
            {
                System.out.println("评论个数未达到情感分析要求！");
            }
            else
            {
                System.out.println("统计结果："+emotionAnalysis.getEMO_ANALYSIS()+" 词云图结果："+emotionAnalysis.getCloud());
            }

        }
    }

    @Test
    void filterActivity() {
        List<Activity> activities=new ArrayList<>();
        activities.add(new Activity("2022-5-31 09:00-11:00","短期"));     //短期未开始
        activities.add(new Activity("2021-4-27 09:00-10:00","短期"));     //短期已结束
        activities.add(new Activity("2022-05-15 至 2022-05-31","长期"));  //长期进行中
        activities.add(new Activity("2022-06-01 至 2022-06-20","长期"));  //长期未开始
        activities.add(new Activity("2021-04-11 至 2021-04-13","长期"));  //长期已结束
        Mockito.when(activityMapper.filterActivity(Mockito.anyString(),Mockito.anyInt(),Mockito.anyString())).thenReturn(activities);

        //mock输入数据
        List<Activity> activities1=activityService.filterActivity("","未开始",1,"数据库");
        List<Activity> activities2=activityService.filterActivity("学术,志愿","",1,"test");
        List<Activity> activities3=activityService.filterActivity("娱乐","已结束",0,"test");
        List<Activity> activities4= activityService.filterActivity("庆典","进行中",1,"");
        List<Activity> activities5=activityService.filterActivity("","",0,"");
        List<List<Activity>> activityList=new ArrayList<>();
        activityList.add(activities1);
        activityList.add(activities2);
        activityList.add(activities3);
        activityList.add(activities4);
        activityList.add(activities5);
        for(int i=0;i<activityList.size();i++)
        {
            System.out.println("第"+i+"次测试：");
            for(int j=0;j<activityList.get(i).size();j++)
            {
                System.out.println(activityList.get(i).get(j).getDate());
            }
        }


    }
}