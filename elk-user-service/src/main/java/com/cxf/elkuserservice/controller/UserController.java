package com.cxf.elkuserservice.controller;

import com.cxf.common.dto.RespDTO;
import com.cxf.elkuserservice.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author always_on_the_way
 * @date 2019-07-23
 */
@RestController
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public RespDTO register(String username, String password){
        return userService.register(username,password);
    }

    @PostMapping("/login")
    public RespDTO login(String username,String password){
        return userService.login(username,password);
    }

}
