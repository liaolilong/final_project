package com.wlaq.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wlaq"})//指定扫描位置
@MapperScan("com.wlaq.educms.mapper")
public class CmsApplication {
    public static void main(String[] args){
        SpringApplication.run(CmsApplication.class,args);
    }
}
