package com.cxf.elkuserservice.service.impl;

import com.cxf.common.dto.RespDTO;
import com.cxf.common.exception.CommonException;
import com.cxf.common.exception.ErrorCode;
import com.cxf.elkuserservice.client.AuthServiceClient;
import com.cxf.elkuserservice.dao.IUserDao;
import com.cxf.elkuserservice.dto.LoginDTO;
import com.cxf.elkuserservice.pojo.JWT;
import com.cxf.elkuserservice.pojo.User;
import com.cxf.elkuserservice.service.IUserService;
import com.cxf.elkuserservice.utils.BPwdEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author always_on_the_way
 * @date 2019-07-23
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private AuthServiceClient authServiceClient;

    @Override
    public RespDTO register(String username, String password) {
        //判断用户名是否存在
        if (existUserByUsername(username)){
            throw new CommonException(ErrorCode.USER_REGISTER_ERROR);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtils.getPwd(password));
        userDao.save(user);
        return RespDTO.onSuc(user);
    }

    private boolean existUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user != null;
    }

    @Override
    public RespDTO login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null){
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        if (!BPwdEncoderUtils.matches(password,user.getPassword())){
            throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
        }
        //获取令牌 oa-uaa-service:123456  base64
        JWT token = authServiceClient.getToken("Basic b2EtdWFhLXNlcnZpY2UlM0ExMjMoNTY=",
                "password", username, password);

        if (token == null){
            throw new CommonException(ErrorCode.GET_TOKEN_FAIL);
        }

        //返回信息
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser(user);
        loginDTO.setToken(token.getAccess_token());

        return RespDTO.onSuc(loginDTO);
    }
}
