package com.cxf.elkuserservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务的配置
 *
 * @author always_on_the_way
 * @date 2019-07-24
 */
@Configuration
@EnableResourceServer
public class OAth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("elk-user-service").tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
               .authorizeRequests()
               .antMatchers("/swagger-resources/**","/v2/**","/webjar/**","/swagger-ui.html").permitAll()
               .antMatchers("/actuator/**").permitAll()
               .antMatchers("/login","/register").permitAll()
               .antMatchers("/**").authenticated();
    }
}
