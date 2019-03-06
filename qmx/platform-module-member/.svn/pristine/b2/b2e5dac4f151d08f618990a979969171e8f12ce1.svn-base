package com.qmx.member.front.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import com.qmx.front.interceptor.MaliciousRequestInterceptor;
//import com.qmx.front.interceptor.TokenInterceptor;
import com.qmx.base.core.interceptor.MaliciousRequestInterceptor;
import com.qmx.member.front.interceptor.MemberInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @Author liubin
 * @Description
 * @Date Created on 2017/12/11 11:04.
 * @Modified By
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //统一静态资源目录
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/resources/");

        //下面的要修改，后面要删除
        registry.addResourceHandler("/common/**")
                .addResourceLocations("classpath:/static/common/");
//        registry.addResourceHandler("/marketing/**")
//                .addResourceLocations("classpath:/static/marketing/");
        registry.addResourceHandler("/member/**")
                .addResourceLocations("classpath:/static/member/");
//        registry.addResourceHandler("/pcshop/**")
//                .addResourceLocations("classpath:/static/pcshop/");
//        registry.addResourceHandler("/shop/**")
//                .addResourceLocations("classpath:/static/shop/");
//        registry.addResourceHandler("/wx/**")
//                .addResourceLocations("classpath:/static/wx/");
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 系统Token拦截器
         */
        /*registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("*//**").excludePathPatterns("/open*//**")
         .excludePathPatterns("/payNotify*//**")
         .excludePathPatterns("/api*//**")
         .excludePathPatterns("/error");*/

        /**
         * 恶意请求拦截器
         */
        registry.addInterceptor(maliciousRequestInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/resources/**");

        //super.addInterceptors(registry);

        /**
         * 会员登录拦截
         */
        registry.addInterceptor(memberInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/member/css/**")
                .excludePathPatterns("/member/js/**")
                .excludePathPatterns("/member/fonts/**")
                .excludePathPatterns("/member/images/**")
                .excludePathPatterns("/member/font-awesome-4.7.0/**")
                .excludePathPatterns("/member/error")
                .excludePathPatterns("/member/login")
                .excludePathPatterns("/member/sendMessage")
                .excludePathPatterns("/member/findByMoblie");

    }

    /**
     * 系统Token拦截器
     * @return
     */
    /*@Bean
    TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }*/


    /**
     * 恶意请求拦截器
     *
     * @return
     */
    @Bean
    MaliciousRequestInterceptor maliciousRequestInterceptor() {
        MaliciousRequestInterceptor maliciousRequestInterceptor = new MaliciousRequestInterceptor();
        maliciousRequestInterceptor.setMaxMaliciousTimes(16);
        maliciousRequestInterceptor.setMinRequestIntervalTime(500L);
        return maliciousRequestInterceptor;
    }

    /**
     * 会员登录拦截
     *
     * @return
     */
    @Bean
    MemberInterceptor memberInterceptor() {
        return new MemberInterceptor();
    }

}
