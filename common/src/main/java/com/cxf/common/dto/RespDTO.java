package com.cxf.common.dto;

import java.io.Serializable;

/**
 * 统一返回json数据格式的类
 *
 * @author always_on_the_way
 * @date 2019-07-23
 */
public class RespDTO<T> implements Serializable {

    public int code = 0;
    public String error = "";
    public T data;

    public static RespDTO onSuc(Object data){
        RespDTO respDTO = new RespDTO();
        respDTO.data = data;
        return respDTO;
    }

    @Override
    public String toString() {
        return "RespDTO{" +
                "code=" + code +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}
