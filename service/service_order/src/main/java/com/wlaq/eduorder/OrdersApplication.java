package com.wlaq.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.wlaq"})
@MapperScan("com.wlaq.eduorder.mapper")
public class OrdersApplication {
    public static void main(String[] args){
        SpringApplication.run(OrdersApplication.class,args);
    }
}
