package com.diancall.platf.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.diancall.platf.config.db.DBTypeEnum;
import com.diancall.platf.config.db.DynamicDataSource;
import com.diancall.platf.config.properties.CustDataSourceProperties;
import com.diancall.platf.config.properties.MerchDataSourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:mybatis-plus配置
 * User: Tiki
 * Date: 2017-10-31
 * Time: 19:59
 */
@Configuration
@MapperScan({"com.diancall.platf.biz.dao.*"})
public class MybatisPlusConfig {

    @Autowired
    CustDataSourceProperties custDataSourceProperties;

    @Autowired
    MerchDataSourceProperties merchDataSourceProperties;

    private DruidDataSource custDataSource() {
        DruidDataSource custDataSource = custDataSourceProperties.config(new DruidDataSource());
        return custDataSource;
    }

    private DruidDataSource merchDataSource(){
        DruidDataSource merchDataSource = merchDataSourceProperties.config(new DruidDataSource());
        return merchDataSource;
    }

    @Bean
    public DynamicDataSource mutiDataSource(/*@Qualifier("custDataSource") DruidDataSource custDataSource, @Qualifier("merchDataSource") DruidDataSource merchDataSource*/) {

        DruidDataSource custDataSource = custDataSource();
        DruidDataSource merchDataSource = merchDataSource();

        try {
            custDataSource.init();
            merchDataSource.init();
        }catch (SQLException sql){
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap.put(DBTypeEnum.CUST_DATA_SOURCE.getValue(), custDataSource);
        hashMap.put(DBTypeEnum.MERCH_DATA_SOURCE.getValue(), merchDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(custDataSource);
        return dynamicDataSource;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

//    @Bean
//    public DataScopeInterceptor dataScopeInterceptor() {
//        return new DataScopeInterceptor();
//    }

}
