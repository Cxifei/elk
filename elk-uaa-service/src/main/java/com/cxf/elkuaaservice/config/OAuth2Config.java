package com.cxf.elkuaaservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * 授权的配置
 *
 * @author always_on_the_way
 * @date 2019-07-23
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager manager;

    /**
     * 配置令牌的安全约束
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();

    }


    /**
     * 配置客户端详情服务
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("elk-uaa-service")
                .secret("{noop}123456")
                .scopes("service")
                .autoApprove(true)
                .authorizedGrantTypes("implicit","refresh_token","password","authorization_code")
                .accessTokenValiditySeconds(24*3600);

    }

    /**
     * 用来配置授权以及令牌的访问
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(jwtTokenEnhancer())
                .authenticationManager(manager);

    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    /**
     * 用来生成token的转换器，每一个token令牌都有签名，每一个签名的加密和验证方式都有两种（对称加密和非对称加密）
     * 使用非对称加密时，需要先生成相应的证书
     * 在Java中可以使用keytool生成jks文件（证书）
     *
     * 生成jks文件命令：
     * keytool -genkeypair -alias cxf-clk -keyalg RSA -keypass 123456 -keystore cxf-clk.jks -storepass 123456
     * 一连串问题按回车，最后一道问题按 y 即可生成证书，一般生成在主机用户目录下
     *
     * 导出公钥：
     * keytool -list -rfc --keystore cxf-clk.jks | openssl x509 -inform pem -pubkey
     *
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        //导入证书
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("cxf-clk.jks"),"123456".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("cxf-clk"));
        return converter;
    }
}
