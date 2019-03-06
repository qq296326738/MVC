package com.qmx.member.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableAsync
//@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients("com.qmx")
@ComponentScan(basePackages = "com.qmx")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class PlatformMemberWebApplication extends SpringBootServletInitializer {


	@Bean
	//@LoadBalanced
	public RestTemplate loadBalancedRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectionRequestTimeout(5000);
		httpRequestFactory.setConnectTimeout(6000);
		httpRequestFactory.setReadTimeout(6000);
		return new RestTemplate(httpRequestFactory);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PlatformMemberWebApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(PlatformMemberWebApplication.class, args);
	}
}
