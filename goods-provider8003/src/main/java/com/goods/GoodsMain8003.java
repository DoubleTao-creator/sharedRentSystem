package com.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GoodsMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(GoodsMain8003.class, args);
    }
}
