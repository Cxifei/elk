package com.cxf.elkuserservice.pojo;

import lombok.Data;

/**
 * 令牌对象
 * @author always_on_the_way
 * @date 2019-07-24
 */
@Data
public class JWT {

    private String access_token;

    private String token_type;

    private String refresh_token;

    private int expires_in;

    private String scope;

    private String jti;
}
