package com.diancall.platf.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:mybatis-plus配置
 * User: Tiki
 * Date: 2017-10-31
 * Time: 19:59
 */
@Configuration
@MapperScan("com.diancall.platf.biz.dao*")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
