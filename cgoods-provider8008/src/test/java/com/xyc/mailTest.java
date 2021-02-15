package com.xyc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class mailTest {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void test0215(){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("极限一带四租赁平台商品审核结果来信");
        mailMessage.setText("亲爱的"+"周游"
                +", 您的商品"+"Lamborghini"
                +"已经通过平台审核");
        mailMessage.setTo("2414418056@qq.com");
        mailMessage.setFrom("1830069482@qq.com");

        mailSender.send(mailMessage);
    }

}
