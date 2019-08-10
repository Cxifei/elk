package com.cxf.elkuserservice.client;

import com.cxf.elkuserservice.client.hystrix.AuthServiceClientHystrix;
import com.cxf.elkuserservice.pojo.JWT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用授权中心相关方法
 *
 * @author always_on_the_way
 * @date 2019-07-24
 */
@FeignClient(value = "elk-uaa-service",fallback = AuthServiceClientHystrix.class)
public interface AuthServiceClient {

    /**
     * 获取JWT
     * @param Authorization
     * @param type
     * @return
     */
    @PostMapping("/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization")String Authorization,
                 @RequestParam(value = "grant_type")String type,
                 @RequestParam(value = "username")String username,
                 @RequestParam(value = "password")String password);

}
