package com.cxf.elkuserservice.utils;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码加密
 *
 * @author always_on_the_way
 * @date 2019-07-23
 */
public class BPwdEncoderUtils {

    private static final PasswordEncoder ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * 获取密码
     * @param pwd
     * @return
     */
    public static String getPwd(String pwd){
        return ENCODER.encode(pwd);
    }

    /**
     * 比较密码
     * @param oldPwd 旧密码
     * @param encodedPwd 加密后的密码
     * @return
     */
    public static boolean matches(CharSequence oldPwd,String encodedPwd){
        return ENCODER.matches(oldPwd,encodedPwd);
    }

}
