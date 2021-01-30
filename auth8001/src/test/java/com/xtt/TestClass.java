package com.xtt;

import com.xtt.mapper.UserMapper;
import com.xtt.util.MailUtils;
import entity.FTPConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import sun.net.ftp.FtpClient;
import utils.MD5Utils;
import utils.PhotoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
public class TestClass {
    @Autowired
    private MailUtils mailUtils;
    @Autowired
    private UserMapper userMapper;
    /**
     * 测试上传文件
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        FTPConstants ftpConstants=new FTPConstants();
        ftpConstants.setInput(new FileInputStream(new File("D:\\images\\default_avatar.png")));
        ftpConstants.setFilename("1.png");
        System.out.println(ftpConstants.toString());
        Boolean result=PhotoUtils.uploadFile(ftpConstants);
        System.out.println(result);
    }

    /**
     * 测试MD5加密
     */
    @Test
    public void testMD5(){
        String s1= MD5Utils.encode("123456");
        System.out.println("第一次加密："+s1);
        String s2=MD5Utils.encode("123456");
        System.out.println("第二次加密："+s2);
        System.out.println(s1.equals(s2));
    }

    /**
     * 测试文件删除
     * @throws IOException
     */
    @Test
    public void testDeleteFile() throws IOException {
        FTPConstants ftpConstants=new FTPConstants();
        ftpConstants.setFilename("1.txt");
        PhotoUtils.deleteFile(ftpConstants);
    }

    /**
     * 测试发送邮件
     */
    @Test
    public void testMailSender(){
        System.out.println(mailUtils.sendEmail("2045519528@qq.com"));
    }
    @Test
    public void test2(){
        System.out.println(userMapper);
    }
}
