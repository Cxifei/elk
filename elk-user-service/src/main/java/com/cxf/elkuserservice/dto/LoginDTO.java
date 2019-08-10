package com.cxf.elkuserservice.dto;

import com.cxf.elkuserservice.pojo.User;
import lombok.Data;

/**
 * @author always_on_the_way
 * @date 2019-07-24
 */
@Data
public class LoginDTO {

    private User user;
    private String token;

}
