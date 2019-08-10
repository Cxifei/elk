package com.cxf.elkuserservice.dao;

import com.cxf.elkuserservice.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author always_on_the_way
 * @date 2019-07-23
 */
public interface IUserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
