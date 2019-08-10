package com.cxf.common.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 实体工具类
 * 对常用的字段进行快速注入值
 *
 * @author always_on_the_way
 * @date 2019-07-22
 */
public final class EntityUtils {

    /**
     * 设置修改和更新相关字段
     * @param t
     * @param <T>
     */
    public static <T> void setCreateAndUpdateInfo(T t){
        setCreateInfo(t);
        setUpdateInfo(t);
    }

    private static <T> void setCreateInfo(T t) {
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获得内部类对象
        UserInfo info = new UserInfo(request).invoke();

        //要填充的字段
        String[] fields = {"createUser","createName","createHost","createTime"};
        //要填充的数据
        Object[] values = {info.getId(),info.getName(),info.getHost(),new Date()};

        //填充数据
        setDefaultValues(t,fields,values);

    }

    public static <T> void setDefaultValues(T t, String[] fields, Object[] values) {
        for (int i = 0; i < fields.length; i++) {
            //获取对象的该字段
            String field = fields[i];
            //判断是否有该字段，如果有，则填充相应的数据
            if (ReflectionUtils.hasField(t,field)){
                //赋值
                ReflectionUtils.invokeSetter(t,field,values[i]);
            }
            //获取对象的该字段

        }

    }


    public static <T> void setUpdateInfo(T t){

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获得内部类对象
        UserInfo info = new UserInfo(request).invoke();

        //要填充的字段
        String[] fields = {"updateUser","updateName","updateHost","updateTime"};
        //要填充的数据
        Object[] values = {info.getId(),info.getName(),info.getHost(),new Date()};

        //填充数据
        setDefaultValues(t,fields,values);

    }

    private static class UserInfo{

        private HttpServletRequest request;
        private String host = "";
        private String name = "";
        private String id = "";

        public UserInfo(HttpServletRequest request) {
            this.request = request;
        }

        public String getHost() {
            return host;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        /**
         * 设置用户相关信息
         * @return
         */
        UserInfo invoke(){
            //获取用户相关信息
            String userName = request.getHeader("userName");
            String userId = request.getHeader("userId");
            String userHost = request.getHeader("userHost");

            if (!StringUtils.isEmpty(userName)){
                name = userName;
            }
            if (!StringUtils.isEmpty(userId)){
                id = userId;
            }
            if (!StringUtils.isEmpty(userHost)){
                host = userHost;
            }
            return this;
        }

    }

}
