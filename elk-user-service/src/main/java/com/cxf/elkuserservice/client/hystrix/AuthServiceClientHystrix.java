package com.cxf.elkuserservice.client.hystrix;

import com.cxf.elkuserservice.client.AuthServiceClient;
import com.cxf.elkuserservice.pojo.JWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author always_on_the_way
 * @date 2019-07-24
 */
@Component
@Slf4j
public class AuthServiceClientHystrix implements AuthServiceClient {
    /**
     * 获取JWT
     *
     * @param authorization
     * @param type
     * @param username
     * @param password
     * @return
     */
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        log.error("调用方法{}异常，参数为：{},{},{}","getToken");
        return null;
    }
}
