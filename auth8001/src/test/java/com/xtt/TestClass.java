package com.xtt;

import entity.FTPConstants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.net.ftp.FtpClient;
import utils.MD5Utils;
import utils.PhotoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
public class TestClass {
    /**
     * 测试上传文件
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        FTPConstants ftpConstants=new FTPConstants();
        ftpConstants.setInput(new FileInputStream(new File("D:\\images\\default_avatar.png")));
        ftpConstants.setFilename("default_avatar.png");
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
}
