package com.zhuangf.frame.base.dao.mybatis;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 基础Mapper接口,接口多继承。(通用数据库)
 * TkMyBatis主要使用的是Jpa注解。
 * 如果数据库用下划线命名法，模型类用驼峰命名法，不需要设置，TkMyBatis默认开启转换。
 * @author: zhuangf
 * @create: 2018-09-27 15:18
 **/
public interface CommonBaseMapper<T> extends Mapper<T>,
        ConditionMapper<T>,//按条件
        IdsMapper<T>{
}
