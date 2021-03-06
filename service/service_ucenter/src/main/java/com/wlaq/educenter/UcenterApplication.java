package com.wlaq.educenter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.wlaq"})
@SpringBootApplication
@MapperScan("com.wlaq.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args){
        SpringApplication.run(UcenterApplication.class);
    }
}
