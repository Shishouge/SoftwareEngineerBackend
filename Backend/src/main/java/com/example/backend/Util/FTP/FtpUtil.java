package com.example.backend.Util.FTP;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class FtpUtil {
    //ftp的服务器ip地址
    private static final String FTP_ADDREES = "101.132.138.14";
    //端口号
    public static final int FTP_PORT = 21;
    //用户名
    public static final String FTP_USERNAME = "user-file";
    //密码
    public static final String FTP_PASSWORD = "123";
    //附件路径
    public static final String FTP_BASE = "/home/user-file/files";

    public static String uploadFile(String FTP_ADDPATH, MultipartFile multipartFile) throws IOException {


        String FTP_BASEPATH = FTP_BASE;
        //获取上传的文件流
        InputStream inputStream = multipartFile.getInputStream();
        //获取上传的文件名
        String filename = multipartFile.getOriginalFilename();
        //截取后缀
        String suffix = filename.substring(filename.lastIndexOf("."));

        //使用UUID拼接后缀，定义一个不重复的文件名
        // String finalName = UUID.randomUUID()+suffix;

        //！！为了可以替换我们的文件，我们使用原来文件本身的名字
        String finalName = multipartFile.getOriginalFilename();

        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(FTP_ADDREES, FTP_PORT);//链接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);//登陆
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();//没连上断开
                return null;
            }
            //basePath+filePath-->/home/user-file/files /2019/09/02
            if (!ftp.changeWorkingDirectory(FTP_BASEPATH + FTP_ADDPATH)) {
                //截取filePath:2019/09/02-->String[]:2019,09,02
                String[] dirs = FTP_ADDPATH.split("/");
                for (String dir : dirs) {
                    //循环数组(第一次循环-->2019)
                    if (null == dir || "".equals(dir))
                        continue;//跳出本地循环，进入下一次循环
                    //更换临时路径：/home/ftp/www/2019
                    FTP_BASEPATH += "/" + dir;
                    //再次检测路径是否存在(/home/ftp/www/2019)-->返回false，说明路径不存在
                    if (!ftp.changeWorkingDirectory(FTP_BASEPATH)) {
                        //makeDirectory():创建目录  返回Boolean雷类型，成功返回true
                        if (!ftp.makeDirectory(FTP_BASEPATH)) {
                            return "make path wrong";
                        } else {
                            //严谨判断（重新检测路径是否真的存在(检测是否创建成功)）
                            ftp.changeWorkingDirectory(FTP_BASEPATH);
                        }
                    }
                }
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);//设置为二进制文件
            //ftp.makeDirectory(FTP_BASEPATH);//创建文件夹
            //ftp.changeWorkingDirectory(FTP_BASEPATH);//将路径转到文件夹
            ftp.enterLocalPassiveMode();
            ftp.storeFile(finalName, inputStream);
            inputStream.close();//关闭
            ftp.logout();//注销
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return finalName;
    }
}