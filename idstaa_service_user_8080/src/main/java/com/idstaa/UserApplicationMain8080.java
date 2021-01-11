package com.idstaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chenjie
 * @date 2021/1/4 11:21
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.idstaa.pojo")
public class UserApplicationMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(UserApplicationMain8080.class,args);
    }
}
