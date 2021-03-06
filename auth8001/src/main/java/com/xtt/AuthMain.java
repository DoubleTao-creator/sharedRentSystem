package com.xtt;
/**
 * @author xtt
 * @date 2021/1/13
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
public class AuthMain {
    public static void main(String    [] args) {
        SpringApplication.run(AuthMain.class,args);
    }
}
