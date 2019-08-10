package com.cxf.common.service;

import java.io.Serializable;
import java.util.List;

/**
 * 基本的CRUD接口
 *
 * @author always_on_the_way
 * @date 2019-07-22
 */
public interface IBaseService<T,ID extends Serializable> {

    /**
     * 保存
     * @param t
     * @return
     */
    boolean save(T t);

    /**
     * 保存不为null的值
     * @param t
     * @return
     */
    boolean saveSelective(T t);

    /**
     * 删除所有
     * @return
     */
    boolean delete();

    /**
     * 根据条件删除
     * @param t
     * @return
     */
    boolean delete(T t);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    boolean deleteById(ID id);


    /**
     * 根据ID更新
     * @param t
     * @return
     */
    boolean updateById(T t);

    /**
     * 根据ID更新，不更新为null的值
     * @param t
     * @return
     */
    boolean updateSelectiveById(T t);

    /**
     * 查询一条
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T selectById(ID id);

    /**
     * 查询所有
     * @return
     */
    List<T> selectAll();

    /**
     * 根据条件查询
     * @param t
     * @return
     */
    List<T> select(T t);

    /**
     * 查询总条数
     * @param t
     * @return
     */
    Long selectCount(T t);

}
