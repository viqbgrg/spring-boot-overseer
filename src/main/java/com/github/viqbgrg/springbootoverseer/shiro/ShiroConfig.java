package com.github.viqbgrg.springbootoverseer.shiro;

import com.github.viqbgrg.springbootoverseer.user.service.IUserService;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Map;

/**
 * shiro 配置文件
 *
 * @author nnj
 */
@Configuration
public class ShiroConfig {


    @Bean
    public Realm userRealm(IUserService userService) {
        UserRealm myShiroRealm = new UserRealm(userService);
        return myShiroRealm;
    }

    @Bean
    public Realm jwtRealm(JwtCredentialsMatcher jwtCredentialsMatcher, IUserService userService) {
        JwtRealm myShiroRealm = new JwtRealm(jwtCredentialsMatcher, userService);
        return myShiroRealm;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = filterFactoryBean.getFilters();
        filterMap.put("jwtToken", jwtAuthFilter());
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
        filterFactoryBean.setFilters(filterMap);
        return filterFactoryBean;
    }

    @Bean
    protected FilterRegistrationBean filterShiroFilterRegistrationBean(ShiroFilterFactoryBean shiroFilterFactoryBean) throws Exception {

        FilterRegistrationBean<AbstractShiroFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);
        filterRegistrationBean.setFilter((AbstractShiroFilter) shiroFilterFactoryBean.getObject());
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/user/login", "noSessionCreation,anon");
        chainDefinition.addPathDefinition("/user/signIn", "noSessionCreation,anon");
        chainDefinition.addPathDefinition("/**", "noSessionCreation,jwtToken");
        return chainDefinition;
    }

    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    @Bean
    public Authenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }

    protected JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter();
    }

}
