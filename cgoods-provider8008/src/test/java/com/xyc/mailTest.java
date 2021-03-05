//package com.xyc;
//
//import entity.FTPConstants;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.test.context.junit4.SpringRunner;
//import utils.PhotoUtils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class mailTest {
//
//    @Autowired
//    JavaMailSenderImpl mailSender;
//
//    @Test
//    public void test0215(){
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setSubject("极限一带四租赁平台商品审核结果来信");
//        mailMessage.setText("亲爱的"+"周游"
//                +", 您的商品"+"Lamborghini"
//                +"已经通过平台审核");
//        mailMessage.setTo("2414418056@qq.com");
//        mailMessage.setFrom("1830069482@qq.com");
//
//        mailSender.send(mailMessage);
//    }
//
//    @Test
//    public void test0217(){
//        try {
//            FTPConstants fc = new FTPConstants();
//            fc.setFilename(PhotoUtils.GOODS_PREFIX+"Nikon5600"+PhotoUtils.SUFFIX);
//            PhotoUtils.deleteFile(fc);
////            fc.setInput(new FileInputStream(new File("C:\\Users\\徐一婵\\IdeaProjects\\work\\oracle\\src\\main\\webapp\\static\\images\\3.jpg")));
////            PhotoUtils.uploadFile(fc);
//            //删除本地临时文件 C:\UserData\AppData\Local\Temp目录下
////            PhotoUtils.deleteTempFile(file);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
