package com.zhuangf.frame.base.dao;

import tk.mybatis.mapper.common.*;

/**
 * 基础Mapper接口,接口多继承。(Mysql)
 * TkMyBatis主要使用的是Jpa注解。
 * 如果数据库用下划线命名法，模型类用驼峰命名法，不需要设置，TkMyBatis默认开启转换。
 * @author: zhuangf
 * @create: 2018-09-27 15:18
 **/
public interface MysqlBaseMapper<T> extends Mapper<T>,
        ConditionMapper<T>,//按条件
        IdsMapper<T>,//根据多个id
        MySqlMapper<T> {
}
