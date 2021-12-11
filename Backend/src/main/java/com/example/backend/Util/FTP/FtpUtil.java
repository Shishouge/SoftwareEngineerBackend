package com.example.backend.Util.FTP;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
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
//        String filename = multipartFile.getOriginalFilename();
//        //截取后缀
//        String suffix = filename.substring(filename.lastIndexOf("."));
//        //使用UUID拼接后缀，定义一个不重复的文件名
//        // String finalName = UUID.randomUUID()+suffix;

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

    public static String deleteFile(String FTP_ADDPATH,String fileName) throws IOException {
        String FTP_BASEPATH = FTP_BASE;
        boolean success = false;
        FTPClient ftp = new FTPClient();
        int reply;
        ftp.connect(FTP_ADDREES, FTP_PORT);//链接FTP服务器
        ftp.login(FTP_USERNAME, FTP_PASSWORD);//登陆
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();//没连上断开
            return "没连上";
        }
        String FTP_PATH = FTP_BASEPATH+FTP_ADDPATH;
        try{
			boolean a = ftp.changeWorkingDirectory(FTP_PATH);
            if(a==false){return "没切换";}
			boolean o = ftp.deleteFile(fileName);
            ftp.logout();
            if(o == true) {
                return "删除成功";
            }
            else {return "删除失败";}
        } catch (Exception e) {
            throw  new IllegalStateException("FTP文件删除失败！" + e.toString());
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    throw  new IllegalStateException("FTP文件删除失败！" + ioe.toString());
                }
            }
        }
    }


    //
//    public static void delete(String deleteFiles) throws IOException {
//        FTPClient ftp = new FTPClient();
//            int reply;
//            ftp.connect(FTP_ADDREES, FTP_PORT);//链接FTP服务器
//            ftp.login(FTP_USERNAME, FTP_PASSWORD);//登陆
//            reply = ftp.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                ftp.disconnect();//没连上断开
//            }
//            deleteServerFiles(ftp,deleteFiles);
//    }
//    public static void deleteServerFiles(FTPClient ftpClient, String deleteFiles) throws IOException {
//
//        /**如果 FTP 连接已经关闭，或者连接无效，则直接返回*/
//        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
//            System.out.println(">>>>>FTP服务器连接已经关闭或者连接无效*****放弃文件上传****");
//            //return;
//        }
//        try {
//            /** 尝试改变当前工作目录到 deleteFiles
//             * 1）changeWorkingDirectory：变更FTPClient当前工作目录，变更成功返回true，否则失败返回false
//             * 2）如果变更工作目录成功，则表示 deleteFiles 为服务器已经存在的目录
//             * 3）否则变更失败，则认为 deleteFiles 是文件，是文件时则直接删除
//             */
//            boolean changeFlag = ftpClient.changeWorkingDirectory(deleteFiles);
//            if (changeFlag) {
//                /**当被删除的是目录时*/
//                FTPFile[] ftpFiles = ftpClient.listFiles();
//                for (FTPFile ftpFile : ftpFiles) {
//                    System.out.println("----------------::::" + ftpClient.printWorkingDirectory());
//                    if (ftpFile.isFile()) {
//                        boolean deleteFlag = ftpClient.deleteFile(ftpFile.getName());
//                        if (deleteFlag) {
//                            System.out.println(">>>>>删除服务器文件成功****" + ftpFile.getName());
//                        } else {
//                            System.out.println(">>>>>删除服务器文件失败****" + ftpFile.getName());
//                        }
//                    } else {
//                        /**printWorkingDirectory：获取 FTPClient 客户端当前工作目录
//                         * 然后开始迭代删除子目录
//                         */
//                        String workingDirectory = ftpClient.printWorkingDirectory();
//                        deleteServerFiles(ftpClient, workingDirectory + "/" + ftpFile.getName());
//                    }
//                }
//                /**printWorkingDirectory：获取 FTPClient 客户端当前工作目录
//                 * removeDirectory：删除FTP服务端的空目录，注意如果目录下存在子文件或者子目录，则删除失败
//                 * 运行到这里表示目录下的内容已经删除完毕，此时再删除当前的为空的目录，同时将工作目录移动到上移层级
//                 * */
//                String workingDirectory = ftpClient.printWorkingDirectory();
//                ftpClient.removeDirectory(workingDirectory);
//                ftpClient.changeToParentDirectory();
//            } else {
//                /**deleteFile：删除FTP服务器上的文件
//                 * 1）只用于删除文件而不是目录，删除成功时，返回 true
//                 * 2）删除目录时无效,方法返回 false
//                 * 3）待删除文件不存在时，删除失败，返回 false
//                 * */
//                boolean deleteFlag = ftpClient.deleteFile(deleteFiles);
//                if (deleteFlag) {
//                    System.out.println(">>>>>删除服务器文件成功****" + deleteFiles);
//                } else {
//                    System.out.println(">>>>>删除服务器文件失败****" + deleteFiles);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



}