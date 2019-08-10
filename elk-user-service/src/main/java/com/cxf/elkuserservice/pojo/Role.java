package com.cxf.elkuserservice.pojo;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色
 *
 * @author always_on_the_way
 * @date 2019-07-24
 */
@Entity(name = "elk-role")
public class Role implements GrantedAuthority,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
