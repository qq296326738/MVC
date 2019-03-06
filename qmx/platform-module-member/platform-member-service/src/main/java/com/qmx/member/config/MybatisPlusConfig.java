package com.qmx.member.config;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.qmx.base.core.support.mybatisplus.CustomAutoSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liubin on 2017/7/29.
 */
@Configuration
@MapperScan({"com.qmx.member.mapper*"})
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 自定义SQL注入
     * @return
     */
    @Bean
    public CustomAutoSqlInjector customAutoSqlInjector(){
        return new CustomAutoSqlInjector();
    }

    @Bean//(name = "dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }
}
