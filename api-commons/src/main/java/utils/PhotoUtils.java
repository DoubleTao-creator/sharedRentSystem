package utils;
/**
 * @author xtt
 * @date 2021/1/17
 */
/**
 * 文件的上传
 */
import entity.FTPConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
public class PhotoUtils {
    public static final String BASE_HEAD_PHOTO_URL ="http://120.78.182.170:8080/photo/default_avatar.png";
    public static final String BASE_PREFIX="http://120.78.182.170:8080/photo/";
    public static final String USER_PREFIX="head_user";
    public static final String SELLER_PREFIX="head_seller";
    public static final String LICENSE_PREFIX="license_seller";
    public static final String GOODS_PREFIX="head_goods";
    public static final String SUFFIX=".png";
    public static boolean uploadFile(FTPConstants ftpConstants) throws IOException {
        FTPClient ftpClient=new FTPClient();
        try {
            int reply;
            ftpClient.enterLocalPassiveMode();
            System.out.println("连接FTP服务器");
            ftpClient.connect(ftpConstants.getHost(), ftpConstants.getPort());
            System.out.println("登录");
            ftpClient.login(ftpConstants.getUsername(), ftpConstants.getPassword());

            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return false;
            }
            // 解决上传文件时文件名乱码
            ftpClient.setControlEncoding("utf-8");
            // 设置上传文件的类型为二进制类型
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            System.out.println("切换到上传目录");
            if (!ftpClient.changeWorkingDirectory(ftpConstants.getFilepath())) {
                System.out.println("目录不存在-》创建目录");
                if (!ftpClient.makeDirectory(ftpConstants.getFilepath())) {
                    System.out.println("目录创建失败");
                    return false;
                } else {
                    System.out.println("进入上传目录");
                    ftpClient.changeWorkingDirectory(ftpConstants.getFilepath());
                }
            }
            // 设置缓存区
            ftpClient.setBufferSize(1024);
            // 开通一个端口来传输数据
            System.out.println("上传文件");
            if (!ftpClient.storeFile(ftpConstants.getFilename(), ftpConstants.getInput())) {
                System.out.println("上传失败");
                return false;
            }
            ftpClient.logout();
        } catch (IOException e) {
            System.out.println("文件上传异常");
            e.printStackTrace();
        } finally {
            System.out.println("断开ftp连接");
            ftpClient.disconnect();
        }
        return true;
    }

    /**
     * 删除文件
     *设置FTPConstants传入文件名即可
     * @param ftpConstants
     * @return
     * @throws IOException
     */
    public static Boolean deleteFile(FTPConstants  ftpConstants) throws IOException {
        FTPClient ftpClient=new FTPClient();
        ftpClient.enterLocalPassiveMode();
        System.out.println("连接FTP服务器");
        try {
            ftpClient.connect(ftpConstants.getHost(), ftpConstants.getPort());
            System.out.println("登录");
            ftpClient.login(ftpConstants.getUsername(), ftpConstants.getPassword());
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return false;
            }
            System.out.println("切换目录");
            ftpClient.changeWorkingDirectory(ftpConstants.getFilepath());
            System.out.println("删除文件");
            //根据传入的文件名进行删除
            if(ftpClient.deleteFile(ftpConstants.getFilename())){
                ftpClient.logout();
                System.out.println("文件删除成功");
                return true;
            } else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ftpClient.logout();
        }
        return false;
    }

    /**
     * multipartfile转为file
     * @param multipartFile
     * @return
     */
    public static File transferToFile(MultipartFile multipartFile) {
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            System.out.println(originalFilename);
            String[] filename = originalFilename.split(".");
            for (String s : filename) {
                System.out.println(s);
            }
            //这里有点问题 一直说filename数组越界
            // 然后filename数组打印确实也打印不出东西
            file= File.createTempFile(filename[0], filename[1]);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(prefix);
        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            file.deleteOnExit();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

