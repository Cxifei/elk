package com.cxf.common.service.impl;

import com.cxf.common.service.IBaseService;
import com.cxf.common.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 公共的MongoDB的实现
 *
 * @author always_on_the_way
 * @date 2019-07-22
 */
public class MongodDBBaseServiceImpl<D extends MongoRepository<T,ID>,T,ID extends Serializable> implements IBaseService<T,ID> {

    @Autowired
    private D dao;

    /**
     * 保存
     *
     * @param t
     * @return
     */
    @Override
    public boolean save(T t) {
        EntityUtils.setCreateAndUpdateInfo(t);
        T t1 = dao.insert(t);
        return true;
    }

    /**
     * 保存不为null的值
     *
     * @param t
     * @return
     */
    @Override
    public boolean saveSelective(T t) {
        dao.deleteAll();
        return true;
    }

    /**
     * 删除所有
     *
     * @return
     */
    @Override
    public boolean delete() {
        return false;
    }

    /**
     * 根据条件删除
     *
     * @param t
     * @return
     */
    @Override
    public boolean delete(T t) {
        dao.delete(t);
        return true;
    }

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(ID id) {
        dao.deleteById(id);
        return true;
    }

    /**
     * 根据ID更新
     *
     * @param t
     * @return
     */
    @Override
    public boolean updateById(T t) {
        EntityUtils.setUpdateInfo(t);
        dao.save(t);
        return true;
    }

    /**
     * 根据ID更新，不更新为null的值
     *
     * @param t
     * @return
     */
    @Override
    public boolean updateSelectiveById(T t) {
        return false;
    }

    /**
     * 查询一条
     *
     * @param t
     * @return
     */
    @Override
    public T selectOne(T t) {
        return dao.findOne(Example.of(t)).orElse(null);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public T selectById(ID id) {
        return dao.findById(id).orElse(null);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return dao.findAll();
    }

    /**
     * 根据条件查询
     *
     * @param t
     * @return
     */
    @Override
    public List<T> select(T t) {
        return dao.findAll(Example.of(t));
    }

    /**
     * 查询总条数
     *
     * @param t
     * @return
     */
    @Override
    public Long selectCount(T t) {
        return dao.count(Example.of(t));
    }
}
