package com.laranevans.springrestreact.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Basic configuration to show how to wire up tomcat-jdbc connection pooling.
// You can replace the type of DataSource with whatever makes sense for you.
@Configuration
public class DataConfiguration {

    @Bean
    public DataSource getDataSource() {
        DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(jdbcDriverClassName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        dataSource.setInitialSize(jdbcInitialSize);
        dataSource.setMaxActive(jdbcMaxActive);
        dataSource.setMaxIdle(jdbcMaxIdle);
        dataSource.setMinIdle(jdbcMinIdle);
        return dataSource;
    }

    @Value("${spring.datasource.driverClassName}")
    private String jdbcDriverClassName;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String jdbcUsername;

    @Value("${spring.datasource.password}")
    private String jdbcPassword;

    @Value("${spring.datasource.initialSize}")
    private Integer jdbcInitialSize;

    @Value("${spring.datasource.maxActive}")
    private Integer jdbcMaxActive;

    @Value("${spring.datasource.maxIdle}")
    private Integer jdbcMaxIdle;

    @Value("${spring.datasource.minIdle}")
    private Integer jdbcMinIdle;

}
