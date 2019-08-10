package com.cxf.elkuserservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author always_on_the_way
 * @date 2019-07-24
 */
@RestController
public class TestController {

    @GetMapping("/say")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getSay(){
        return "当前时间为："+System.currentTimeMillis();
    }


}
