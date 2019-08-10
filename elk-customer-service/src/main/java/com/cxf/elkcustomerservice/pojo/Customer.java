package com.cxf.elkcustomerservice.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户
 *
 * @author always_on_the_way
 * @date 2019-07-22
 */
@Data
@Document(collection = "elk_customer")
public class Customer implements Serializable {

    @Id
    private String id;

    private String name;

    private String sex;

    private String age;

    private String school;

    /**
     * 专业
     */
    private String profession;

    /**
     * 学历
     */
    private String education;

    /**
     * 毕业年限
     */
    private String year;

    private String telephone;

    private String qq;

    private String email;

    private String weChat;

    /**
     * 其他联系方式
     */
    private String otherContact;

    /**
     * 现状
     */
    private String presentSituation;

    /**
     * 发展方向
     */
    private String developmentDirection;

    /**
     * 报名时间
     */
    private Date registrationTime;

    /**
     * 到访时间
     */
    private Date visitTime;


    /**
     * 下面几项为一般性属性
     */
    private Date createTime;

    private String createUser;

    private String createName;

    private String createHost;

    private Date updateTime;

    private String updateUser;

    private String updateName;

    private String updateHost;

}
