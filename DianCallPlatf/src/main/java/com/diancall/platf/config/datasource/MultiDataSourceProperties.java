package com.diancall.platf.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-06
 * Time: 17:46
 */
public class MultiDataSourceProperties {

    private final String merchDataSourceName = "merchDataSource";

    @Value("${merch.datasource.url}")
    private final String url = "";

    @Value("${merch.datasource.url}")
    private final String username = "";

    @Value("${merch.datasource.url}")
    private final String password = "";

    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
    }

    public String getMerchDataSourceName() {
        return merchDataSourceName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
