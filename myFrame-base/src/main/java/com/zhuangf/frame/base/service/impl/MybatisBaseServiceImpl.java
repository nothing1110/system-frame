package com.zhuangf.frame.base.service.impl;

import com.zhuangf.frame.base.dao.mybatis.MysqlBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Mybatis服务接口实现类
 * @Author: zhuangf
 * @Date: 2018/10/8
 */
public class MybatisBaseServiceImpl<T,Mapper extends MysqlBaseMapper<T>,ID extends Serializable>
        extends CommonBaseServiceImpl<T,Mapper,ID> {

    @Autowired
    protected Mapper mapper;

    @Override
    public int insertList(List<T> modelList) {
        return mapper.insertList(modelList);
    }

}
