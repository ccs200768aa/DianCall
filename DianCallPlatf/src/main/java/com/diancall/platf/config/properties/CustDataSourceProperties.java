package com.diancall.platf.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-02
 * Time: 11:57
 */
@Configuration
@MapperScan(basePackages = "com.diancall.biz.entity.cust", sqlSessionFactoryRef = "custSqlSessionFactory")
public class CustDataSourceProperties {

    @Value("${cust.datasource.url}")
    private String url;

    @Value("${cust.datasource.username}")
    private String username;

    @Value("${cust.datasource.password}")
    private String password;

    @Bean(name = "custDataSource")
    public DruidDataSource custDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

/*    @Bean(name = "custTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(custDataSource());
    }

    @Bean(name = "custSqlSessionFactory")
    public SqlSessionFactory custSqlSessionFactory(@Qualifier("custDataSource") DataSource custDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(custDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/cust/*.xml"));
        return bean.getObject();
    }*/
}
