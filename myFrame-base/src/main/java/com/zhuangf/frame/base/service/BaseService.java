package com.zhuangf.frame.base.service;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 基础服务接口
 * @Author: zhuangf
 * @Date: 2018/10/8
 */
public interface BaseService<T,ID extends Serializable> {
    /**
     * 插入单个对象
     * @param model
     * @return
     */
    Boolean insert(T model);

    /**
     * 插入多个对象
     * @param modelList
     * @return
     */
    int insertList(List<T> modelList);

    /**
     * 更新单个对象
     * @param model
     * @return
     */
    Boolean updateById(T model);

    /**
     * 更新多个对象
     * @param modelList
     * @return
     */
    int updateByIds(List<T> modelList);

    /**
     * 删除单个对象
     * @param id
     * @return
     */
    Boolean deleteById(ID id);

    /**
     * 删除多个对象
     * @param idList
     * @return
     */
    int deleteByIds(ID... idList);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T queryById(ID id);

    /**
     * 根据多个ID查询
     * @param idList
     * @return
     */
    List<T> queryByIds(ID... idList);

    /**
     * 根据多个ID查询
     * @param ids "1,2,3"
     * @return
     */
    List<T> queryByIds(String ids);

    /**
     * 模糊分页查询
     * @param t
     * @param page
     * @param likeFields
     * @return
     */
    Page<T> queryPage(T t, Page page, String... likeFields);
}
