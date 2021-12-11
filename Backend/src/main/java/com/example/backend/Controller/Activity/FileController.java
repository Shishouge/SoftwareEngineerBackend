package com.example.backend.Controller.Activity;

import com.example.backend.Util.FTP.FtpUtil;
import com.example.backend.Util.Response.AjaxJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(tags="文件管理")

public class FileController {
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
