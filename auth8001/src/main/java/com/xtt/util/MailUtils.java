package com.xtt.util;
/**
 * @author xtt
 * @date 2021/1/24
 */
/**
 * 邮件发送工具类
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import java.util.Random;
@Component
public class MailUtils {
     String sender="2045519528@qq.com";
    @Autowired
     JavaMailSender javaMailSender;

    /**
     * 发送邮件
     * @param email 接收者邮箱地址
     * @return 若发送成功，返回code值,发送失败返回“failure"
     */
    public  String sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        //随机数生成6位验证码
        String code = createCode(6);
        message.setFrom(sender);
        message.setTo(email);
        // 标题
        message.setSubject("智能化共享租赁平台");
        message.setText("【智能化共享租赁平台】你的验证码为："+code+"，(若不是本人操作，可忽略该条邮件)");
        try {
            javaMailSender.send(message);
            return code;
        }catch (Exception e){
            e.printStackTrace();
            return "failure";
        }
    }

    /**
     * 生成n位随机码
     * @param n
     * @return
     */
    private static String createCode(int n){
        Random r = new Random();
        StringBuffer sb =new StringBuffer();
        for(int i = 0;i < n;i ++){
            int ran1 = r.nextInt(10);
            sb.append(String.valueOf(ran1));
        }
        return sb.toString();
    }

}
