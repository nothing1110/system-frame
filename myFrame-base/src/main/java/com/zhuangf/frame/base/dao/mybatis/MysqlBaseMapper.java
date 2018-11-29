package com.zhuangf.frame.base.dao.mybatis;

import com.zhuangf.frame.base.dao.mybatis.CommonBaseMapper;
import tk.mybatis.mapper.common.*;

/**
 * Mysql接口
 * @author: zhuangf
 * @create: 2018-09-27 15:18
 **/
public interface MysqlBaseMapper<T> extends CommonBaseMapper<T>,MySqlMapper<T> {
}
