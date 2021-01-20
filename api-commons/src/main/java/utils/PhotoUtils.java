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
import java.io.IOException;
@Slf4j
public class PhotoUtils {
    public static final String BASE_HEAD_PHOTO_URL ="http://120.78.182.170:8080/photo/default_avatar.png";
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

}
