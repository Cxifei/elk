package com.cxf.elkuserservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * Json token 认证
 *
 * @author always_on_the_way
 * @date 2019-07-24
 */
@Configuration
public class JwtConfig {

    @Autowired
    private JwtAccessTokenConverter tokenConverter;

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(tokenConverter);
    }

    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("public.cert");
        String publickey;
        try {
            publickey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        converter.setVerifierKey(publickey);
        return converter;
    }


}
