package com.xyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableEurekaClient
public class AdministratorMain {
    public static void main(String[] args) {
        SpringApplication.run(AdministratorMain.class,args);
    }
}
