package com.cxf.common.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射的工具类
 *
 * @author always_on_the_way
 * @date 2019-07-22
 */
public final class ReflectionUtils {

    private static final String SETTER_PREFIX = "set";

    /**
     * 判断某个对象是否拥有某个属性
     * @param obj
     * @param fieldName
     * @return
     */
    public static boolean hasField(final Object obj,final String fieldName){
        //获取该字段
        Field field = getAccessibleField(obj,fieldName);
        return field != null;

    }

    /**
     * 获取字段
     * @param obj
     * @param fieldName
     * @return
     */
    public static Field getAccessibleField(final Object obj, final String fieldName) {
        for(Class<?> aClass = obj.getClass();aClass != Object.class;aClass = aClass.getSuperclass()){
            try {
                return aClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 调用set方式
     * @param obj
     * @param field
     * @param value
     */
    public static void invokeSetter(final Object obj, final String field, final Object value) {
        //获取方法名称
        String setterName = SETTER_PREFIX + StringUtils.capitalize(field);
        invokeMethodByName(obj,setterName,value);

    }

    private static Object invokeMethodByName(final Object obj, final String setterName, final Object value) {
        //判断方法是否存在
        Method method = getAccessibleMethod(obj,setterName);
        if (method == null){
            throw new IllegalArgumentException("方法不存在");
        }
        try {
            return method.invoke(obj,value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("方法访问异常");
        }
    }

    /**
     * 获取方法
     * @param obj
     * @param methodName
     * @return
     */
    private static Method getAccessibleMethod(final Object obj,final String methodName) {
        for(Class<?> aClass = obj.getClass();aClass != Object.class;aClass = aClass.getSuperclass()){
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)){
                    return method;
                }
            }
        }
        return null;
    }


}
