package com.cxf.elkuaaservice.dao;

import com.cxf.elkuaaservice.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author always_on_the_way
 * @date 2019-07-24
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
