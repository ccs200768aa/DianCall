package com.diancall.platf.config.web;

import com.diancall.platf.biz.shiro.MerchUserRealm;
import com.diancall.platf.biz.shiro.ShiroRedisCacheManager;
import com.diancall.platf.config.datasource.RedisProperties;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: shiro权限配置
 * User: Tiki
 * Date: 2017-10-30
 * Time: 16:16
 */
@Configuration
public class ShiroConfig {

    /**
     * 缓存管理
     *
     * @return
     */
    @Value("${spring.redis.host}")
    private String host = "127.0.0.1";

    @Value("${spring.redis.password}")
    private String password = "";

    @Value("${spring.redis.port}")
    private int port = 6379;

    @Resource
    RedisProperties redisProperties;


    @Bean
    @DependsOn(value = "redisTemplate")
    public ShiroRedisCacheManager redisCacheManager() {
        ShiroRedisCacheManager cacheManager = new ShiroRedisCacheManager(redisTemplate());
        return cacheManager;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<byte[], Object> redisTemplate() {
        RedisTemplate<byte[], Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory conn = new JedisConnectionFactory();
//        redisProperties.config(conn);
        conn.setHostName(this.host);
        conn.setPassword(this.password);
        conn.setPort(this.port);
        conn.setDatabase(3);
        conn.setTimeout(30000);
        return conn;
    }


    /**
     * remember管理器
     *
     * @param rememberMeCookie
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie);
        cookieRememberMeManager.setCipherKey(org.apache.shiro.codec.Base64.decode("AAAAAXXv"));
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
        return simpleCookie;
    }

    /**
     * 自定义realm
     *
     * @return
     */
    @Bean
    public MerchUserRealm merchUserRealm() {
        return new MerchUserRealm();
    }

    /**
     * session管理器
     *
     * @return
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(redisCacheManager());
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.merchUserRealm());
        securityManager.setRememberMeManager(rememberMeManager(rememberMeCookie()));
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setSessionManager(defaultWebSessionManager());
        return securityManager;
    }

    /**
     * shiro过滤器链
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/");
        shiroFilter.setUnauthorizedUrl("/error");
        //TODO
        Map<String, String> chainMap = new LinkedHashMap<>();
        chainMap.put("/static/**", "anon");
        chainMap.put("/merchuser/**", "authc");
        chainMap.put("/**", "anon");
        shiroFilter.setFilterChainDefinitionMap(chainMap);
        return shiroFilter;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(new Object[]{securityManager});
        return bean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
