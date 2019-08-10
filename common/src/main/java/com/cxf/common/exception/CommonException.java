package com.cxf.common.exception;

/**
 * 公共异常类
 *
 * @author always_on_the_way
 * @date 2019-07-23
 */
public class CommonException extends RuntimeException{

    private ErrorCode errorCode;

    public CommonException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public int getCode(){
        return errorCode.getCode();
    }

    public String getMsg(){
        return errorCode.getMsg();
    }

}
