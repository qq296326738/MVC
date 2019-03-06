package com.qmx.member.web.config;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroLoginConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        //filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->
        //swagger
        filterChainDefinitionMap.put("/swagger-ui.html","authc,roles[admin]");
        filterChainDefinitionMap.put("/swagger-resources*","authc,roles[admin]");
        filterChainDefinitionMap.put("/api-docs","authc,roles[admin]");
        filterChainDefinitionMap.put("/webjars*","authc,roles[admin]");
        filterChainDefinitionMap.put("/doc.html","authc,roles[admin]");
        //静态资源
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/font/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/slider_files/**", "anon");
        filterChainDefinitionMap.put("/403", "anon");
        filterChainDefinitionMap.put("/error/**", "anon");
        filterChainDefinitionMap.put("/resources/**", "anon");

        //匿名访问地址
        filterChainDefinitionMap.put("/file/upload/**", "anon");
        filterChainDefinitionMap.put("/info", "anon");
        filterChainDefinitionMap.put("/trace", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/common/**", "anon");
        filterChainDefinitionMap.put("/0/**", "anon");//二维码地址
        filterChainDefinitionMap.put("/1/**", "anon");//二维码地址
        filterChainDefinitionMap.put("/qr", "anon");//二维码地址
        filterChainDefinitionMap.put("/orderQr", "anon");//二维码地址(反向查订单)
        filterChainDefinitionMap.put("/qrImage/**", "anon");//纯二维码地址(美团)
        filterChainDefinitionMap.put("/payNotify/**", "anon");//支付回调

        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/open/**", "anon");
        filterChainDefinitionMap.put("/internalApi/**","anon");
        //rememberMe
        filterChainDefinitionMap.put("/**", "user");
        //filterChainDefinitionMap.put("/**", "authc,user");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
        shiroFilterFactoryBean.setLoginUrl("/sso/login");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/main");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

}