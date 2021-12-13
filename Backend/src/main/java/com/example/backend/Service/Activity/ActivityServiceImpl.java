package com.example.backend.Service.Activity;

import com.example.backend.Controller.Activity.FileController;
import com.example.backend.DAO.Account.IndividualUserMapper;
import com.example.backend.DAO.Activity.ActivityMapper;
import com.example.backend.Entity.Account.IndividualUser;
import com.example.backend.Entity.Activity.Activity;
import com.example.backend.Entity.Activity.EmotionAnalysis;
import com.example.backend.Entity.Activity.ReviewActivity;
import com.example.backend.Util.Recommend.RecommendHelper;
import com.example.backend.Util.Response.AjaxJson;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.python.util.PythonInterpreter;
import org.springframework.web.multipart.MultipartFile;




@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    IndividualUserMapper individualUserMapper;

    @Override
    public List<Activity> getAllActivities() {
        return activityMapper.getAllActivities();
    }

    @Override
    public List<ReviewActivity> getReviewsByActivity(int ID) {
        return activityMapper.getReviewsByActivity(ID);
    }

    @Override
    public int publishActivity(String title, String img, String organizationID, String date, String place, String form, String introduction, String content, String genres, int capacity, int status) {
        int likeNum = 0;
        int subscriberNum=0;
        return activityMapper.publishActivity(title, img, organizationID, date, place, form, introduction, content, genres, likeNum, capacity, status,subscriberNum);
    }

    @Override
    public int updateActivity(int ID, String title, String img, String organizationID, String date, String place, String form, String introduction, String content, String genres, int capacity, int status) {
        return activityMapper.updateActivity(ID, title, img, organizationID, date, place, form, introduction, content, genres, capacity, status);
    }

    @Override
    public int likeActivity(String individualUserID, int activityID) {
        int result = activityMapper.likeActivity(individualUserID, activityID);
        Activity activity = activityMapper.getLikeNum(activityID);
        int add = activityMapper.addLikeNum(activityID, activity.getLikeNum() + 1);
        return add;
    }

    @Override
    public int unlike(String individualUserID, int activityID) {
        int result = activityMapper.unlike(individualUserID, activityID);
        Activity activity = activityMapper.getLikeNum(activityID);
        int add = activityMapper.addLikeNum(activityID, activity.getLikeNum() - 1);
        return add;
    }

    @Override
    public int review(String individualUserID, int activityID, String content, int score) {
        int check = activityMapper.checkSignUpSitua(individualUserID, activityID);
        if (check == 1) {
            activityMapper.review(individualUserID, activityID, content, score);
        }
        return check;
    }

    @Override
    public int signUpActivity(String individualUserID, int activityID) {
        Activity a=activityMapper.getLikeNum(activityID);
        int result=-1;
        if(a.getSubscriberNum()==a.getCapacity()-1)
        {
            result = activityMapper.signUpActivity(individualUserID, activityID);
            activityMapper.addSubscribNum(activityID,activityMapper.getLikeNum(activityID).getSubscriberNum()+1);
            activityMapper.editStatus(activityID,0);
        }
        else {
            result = activityMapper.signUpActivity(individualUserID, activityID);
            activityMapper.addSubscribNum(activityID,activityMapper.getLikeNum(activityID).getSubscriberNum()+1);
        }
        return result;
    }

    @Override
    public int checkLike(String iID,int aID)
    {
        return activityMapper.checkLike(iID,aID);
    }

    @Override
    public int checkSignUp(String iID,int aID)
    {
        return activityMapper.checkSignUpSitua(iID,aID);
    }

    @Override
    public int cancleSignUp(String iID,int aID)
    {
        int result=activityMapper.cancleSignUp(iID,aID);
        activityMapper.addSubscribNum(aID,activityMapper.getLikeNum(aID).getSubscriberNum()-1);
        return result;
    }

    @Override
    public int deleteActivity(int ID)
    {
        return activityMapper.deleteActivity(ID);
    }

    @Override
    public EmotionAnalysis getEmotionalAnalysis(int ID)
    {
        List<ReviewActivity> reviewActivities1=activityMapper.getReviewsByActivity(ID);
        int size=reviewActivities1.size();
        System.out.println(size);
        if(size%10==0)
        {
            List<ReviewActivity> reviewActivities=activityMapper.getReviewsByActivity(ID);
            String filename="E:\\大三上\\软件工程\\data\\testData\\review.txt";
            try {
                File writeName = new File(filename);
                writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
                try (FileWriter writer = new FileWriter(writeName);
                     BufferedWriter out = new BufferedWriter(writer)
                ) {
                    for(ReviewActivity r:reviewActivities)
                    {
                        String line2=r.getContent();
                        out.write(line2+"\r\n"); // \r\n即为换行
                    }
                    out.flush(); // 把缓存区内容压入文件
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] cmds = new String[]{"python", "E:\\大三上\\软件工程\\课程项目\\代码\\train.py",String.valueOf(ID)};
            Process proc;
            String outPutName="E:\\大三上\\软件工程\\data\\testData\\"+String.valueOf(ID)+".png";
            String cloudName="E:\\大三上\\软件工程\\data\\testData\\"+String.valueOf(ID)+"_cloud.png";
            try {
                proc = Runtime.getRuntime().exec(cmds);// 执行py文件
                //用输入输出流来截取结果
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
                proc.waitFor();

                File pdfFile = new File(outPutName);
                File cloud = new File(cloudName);
                try{
                    FileInputStream fileInputStream = new FileInputStream(pdfFile);
                    MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

                    FileInputStream fileInputStream1 = new FileInputStream(cloud);
                    MultipartFile multipartFile1 = new MockMultipartFile(cloud.getName(), cloud.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream1);

                    FileController fileController=new FileController();
                    AjaxJson path=fileController.addFile("/analysis/",multipartFile);
                    AjaxJson path1=fileController.addFile("/analysis/",multipartFile1);
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            activityMapper.addAnalysis("http://101.132.138.14/files/analysis/"+String.valueOf(ID)+".png",ID);
            activityMapper.addCloud("http://101.132.138.14/files/analysis/"+String.valueOf(ID)+"_cloud.png",ID);
            EmotionAnalysis emotionAnalysis=new EmotionAnalysis("http://101.132.138.14/files/analysis/"+String.valueOf(ID)+".png","http://101.132.138.14/files/analysis/"+String.valueOf(ID)+"_cloud.png");
            return emotionAnalysis;
        }
        else
        {
            EmotionAnalysis emotionAnalysis=activityMapper.getIMGofActivity(ID);
            return emotionAnalysis;
        }

    }
    @Override
    public List<IndividualUser> getUserSubscribed(int ID)
    {
        return activityMapper.getUserSubscribed(ID);
    }

    @Override
    public int deleteReview(String iID,int aID)
    {
        return activityMapper.deleteReview(iID, aID);
    }

    @Override
    public List<Activity> getRecommendActivity(String ID) {
        List<String> PLACE_LABEL = activityMapper.getAllPlaces();
        List<String> ORGANIZER_LABEL = activityMapper.getAllOrgs();
        String CHARACTER_LABEL[] = new String[3];
        CHARACTER_LABEL[0] = "学习";
        CHARACTER_LABEL[1] = "互助";
        CHARACTER_LABEL[2] = "娱乐";
        int totalLabelSize = PLACE_LABEL.size() + ORGANIZER_LABEL.size() + CHARACTER_LABEL.length;
        List<Activity> SActivity = individualUserMapper.getMySignUpActivities(ID);
        List<Activity> NotSActivity=activityMapper.getNotSignUpActivity(ID);
        int [][] itemProfile = new int[NotSActivity.size()][totalLabelSize];
        //为没有报名的活动构建属性列表
        for(int i=0;i< NotSActivity.size();i++)
        {
            for(int j=0;j<PLACE_LABEL.size();j++)
            {
                if(NotSActivity.get(i).getPlace()==PLACE_LABEL.get(j)) {
                    itemProfile[i][j] = 1;
                }
            }
            for(int j=0;j<ORGANIZER_LABEL.size();j++)
            {
                if(activityMapper.getOrgByActivity(NotSActivity.get(i).getOrganizationID())==ORGANIZER_LABEL.get(j)){
                    itemProfile[i][j+PLACE_LABEL.size()]=1;
                }
            }
            for(int j=0;j<CHARACTER_LABEL.length;j++)
            {
                String genres[]=NotSActivity.get(i).getGenres().split(",");
                for(int m=0;m<genres.length;m++)
                {
                    if(genres[m].equals(CHARACTER_LABEL[j]))
                    {
                        itemProfile[i][j+PLACE_LABEL.size()+ORGANIZER_LABEL.size()]=1;
                        break;
                    }
                }
            }
        }

        //根据已报名的活动构建用户属性:对每个属性的喜好值
        int userProfileDemo[]=new int[totalLabelSize];
        int n=0;
        for(int i=0;i<SActivity.size();i++)
        {
//            for(int j=0;j<totalLabelSize;j++)
//            {
                for(int m=0;m< PLACE_LABEL.size();m++)
                {
                    if(SActivity.get(i).getPlace().equals(PLACE_LABEL.get(m)))
                    {
                        userProfileDemo[m]++;
                    }
                }
                for(int m=0;m< ORGANIZER_LABEL.size();m++)
                {
                    if(SActivity.get(i).getPlace().equals(ORGANIZER_LABEL.get(m)))
                    {
                        userProfileDemo[m+ PLACE_LABEL.size()]++;
                    }
                }
                for(int m=0;m< CHARACTER_LABEL.length;m++)
                {
                    String genres[]=NotSActivity.get(i).getGenres().split(",");
                    for(int l=0;l<genres.length;l++)
                    {
                        if(genres[l].equals(CHARACTER_LABEL[m]))
                        {
                            userProfileDemo[m+ PLACE_LABEL.size()+ ORGANIZER_LABEL.size()]++;
                        }
                    }
                }
            //}
        }
        double userProfile[]=new double[userProfileDemo.length];
        for(int i=0;i<userProfileDemo.length;i++)
        {
            if(userProfileDemo[i]==0)
            {
                userProfile[i]=-111111111;
            }
            else
               userProfile[i]=(userProfileDemo[i]-1)/userProfileDemo[i];
        }

        //对每个未报名的活动，计算该用户与其的相似度
        double similarity[]=new double[NotSActivity.size()];
        for(int i=0;i<similarity.length;i++)
        {
            double multi=0.0;
            double ua=0.0;
            double ia=0.0;
            for(int j=0;j<totalLabelSize;j++)
            {
                multi+=userProfile[j]*itemProfile[i][j];
                ua+=ua+ua*ua;
                ia=ia+ia*ia;
            }
            ua=Math.sqrt(ua);
            ia=Math.sqrt(ia);
            similarity[i]=multi/(ua+ia);
        }
        //将未报名的活动按照相似度从大到小排序返回
        List<RecommendHelper> ac=new ArrayList<>();
        for(int i=0;i< NotSActivity.size();i++)
        {
            RecommendHelper helper=new RecommendHelper(NotSActivity.get(i),similarity[i]);
            ac.add(helper);
        }
        Collections.sort(ac);
        List<Activity> sortedActivity=new ArrayList<>();
        for(int i=0;i<ac.size();i++)
        {
            Activity a=ac.get(i).getActivity();
            sortedActivity.add(a);
        }
        return sortedActivity;
    }


}

