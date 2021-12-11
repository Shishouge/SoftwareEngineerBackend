package com.example.backend.Controller.Activity;

import com.example.backend.Util.FTP.FtpUtil;
import com.example.backend.Util.Response.AjaxJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@Api(tags="文件管理")

public class FileController {
//    //本地文件转为MultipartFile类型(文件路径)
//    private static MultipartFile getMulFileByPath(String picPath) {
//        FileItem fileItem = createFileItem(picPath);
//        MultipartFile mfile = new CommonsMultipartFile(fileItem);
//        return mfile;
//    }
//
//    private static FileItem createFileItem(String filePath)
//    {
//        FileItemFactory factory = new DiskFileItemFactory(16, null);
//        String textFieldName = "textField";
//        int num = filePath.lastIndexOf(".");
//        String extFile = filePath.substring(num);
//        FileItem item = factory.createItem(textFieldName, "text/plain", true,
//                "MyFileName" + extFile);
//        File newfile = new File(filePath);
//        long fileSize = newfile.length();
//        int bytesRead = 0;
//        byte[] buffer =new byte[(int) fileSize];
//        try
//        {
//            FileInputStream fis = new FileInputStream(newfile);
//            OutputStream os = item.getOutputStream();
//            while ((bytesRead = fis.read(buffer, 0,  buffer.length))!= -1)
//            {
//                os.write(buffer, 0, bytesRead);
//            }
//            os.close();
//            fis.close();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        return item;
//    }

    @ApiOperation("上传文件到服务器上")

    @PostMapping("addFile")
    public AjaxJson addFile(String addPath,@RequestParam("myFile") MultipartFile multipartFile) throws IOException {

            //调用自定义的FTP工具类上传文件
            String fileName = FtpUtil.uploadFile(addPath, multipartFile);
            return new AjaxJson(200,"上传成功",fileName);
    }

    @ApiOperation("删除服务器上的文件")
    @PostMapping("deleteFile")
    public AjaxJson deleteFile(String filePath,String fileName) throws IOException {
           return new AjaxJson(200, "成功执行操作",FtpUtil.deleteFile(filePath,fileName));
    }

}
