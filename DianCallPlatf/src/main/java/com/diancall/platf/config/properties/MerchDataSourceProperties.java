package com.diancall.platf.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-02
 * Time: 11:57
 */
@Component
//@MapperScan(basePackages = "com.diancall.biz.entity.merch", sqlSessionFactoryRef = "merchSqlSessionFactory")
public class MerchDataSourceProperties {

    @Value("${merch.datasource.url}")
    private String url;

    @Value("${merch.datasource.username}")
    private String username ;

    @Value("${merch.datasource.password}")
    private String password;


//    @Bean(name = "merchDataSource")
//    @Primary
//    public DruidDataSource merchDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl(this.url);
//        dataSource.setUsername(this.username);
//        dataSource.setPassword(this.password);
//        return dataSource;
//    }


    public DruidDataSource config(DruidDataSource dataSource ){
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

/*    @Bean(name = "merchTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(merchDataSource());
    }*/

//    @Bean(name = "merchSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory merchSqlSessionFactory(@Qualifier("merchDataSource") DataSource merchDataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(merchDataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/merch/*.xml"));
//        return bean.getObject();
//    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
