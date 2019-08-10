package com.cxf.common.exception;

/**
 * @author always_on_the_way
 * @date 2019-07-23
 */
public enum ErrorCode {
    //常用
    OK(0,""),
    FAIL(-1,"操作失败"),
    RPC_ERROR(-2,"远程调用失败"),
    USER_NOT_FOUND(1000,"用户不存在"),
    USER_PASSWORD_ERROR(1001,"密码错误"),
    GET_TOKEN_FAIL(1002,"获取token失败"),
    TOKEN_IS_NOT_MATCH_USER(1003,"请使用自己的token进行接口请求"),
    USER_IS_NOT_LOGIN(1004,"该用户没有登录"),
    USER_REGISTER_ERROR(1005,"注册失败");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ErrorCode code(int code){
        for (ErrorCode value : values()) {
            if (value.getCode() == code){
                return value;
            }
        }
        return null;
    }

}
