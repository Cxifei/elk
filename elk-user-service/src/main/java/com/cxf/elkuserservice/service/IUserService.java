package com.cxf.elkuserservice.service;

import com.cxf.common.dto.RespDTO;

/**
 * @author always_on_the_way
 * @date 2019-07-23
 */
public interface IUserService {

    //注册
    RespDTO register(String username,String password);

    //登录
    RespDTO login(String username,String password);

}
