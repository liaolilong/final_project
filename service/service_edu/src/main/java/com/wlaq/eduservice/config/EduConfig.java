package com.wlaq.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wlaq.eduservice.mapper")
public class EduConfig {


/**
 2
 * 逻辑删除插件
 3
 */

    @Bean

    public ISqlInjector sqlInjector() {

        return new LogicSqlInjector();

    }

    /**
     2
     * 分页插件
     3
     */

    @Bean

    public PaginationInterceptor paginationInterceptor() {

        return new PaginationInterceptor();

    }
}

