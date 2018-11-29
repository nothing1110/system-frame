package com.zhuangf.frame.base.service.impl;

import com.github.pagehelper.Page;
import com.zhuangf.frame.base.dao.mybatis.CommonBaseMapper;
import com.zhuangf.frame.base.dao.mybatis.MysqlBaseMapper;
import com.zhuangf.frame.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * 基础服务接口实现类
 * @Author: zhuangf
 * @Date: 2018/10/8
 */
public class CommonBaseServiceImpl<T,Mapper extends CommonBaseMapper<T>,ID extends Serializable>
        implements BaseService<T,ID> {

    @Autowired
    protected Mapper mapper;

    @Override
    public Boolean insert(T model) {
        if(mapper.insertSelective(model)>0) {
            return true;
        }
        return false;
    }

    @Override
    public int insertList(List<T> modelList) {
        int intNum = 0;
        for(T model : modelList) {
            intNum += mapper.insertSelective(model);
        }
        return intNum;
    }

    @Override
    public Boolean updateById(T model) {
        if(mapper.updateByPrimaryKeySelective(model)>0) {
            return true;
        }
        return false;
    }

    @Override
    public int updateByIds(List<T> modelList) {
        int sucNum = 0;
        for(T model : modelList) {
            sucNum += mapper.updateByPrimaryKeySelective(model);
        }
        return sucNum;
    }

    @Override
    public Boolean deleteById(ID id) {
        if(mapper.deleteByPrimaryKey(id)>0) {
            return true;
        }
        return false;
    }

    @Override
    public int deleteByIds(ID... idList) {
        int sucNum = 0;
        for(ID id : idList) {
            sucNum += mapper.deleteByPrimaryKey(id);
        }
        return sucNum;
    }

    @Override
    public T queryById(ID id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> queryByIds(ID... idList) {
        if(idList.length>0) {
            StringBuilder idsBuilder = new StringBuilder();
            for (int i = 0; i < idList.length; i++) {
                idsBuilder.append(idList[i]);
                if(i<idList.length-1) {
                    idsBuilder.append(",");
                }
            }
            return mapper.selectByIds(idsBuilder.toString());
        }
       return null;
    }

    @Override
    public List<T> queryByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    public Page<T> queryPage(T t, Page page, String... likeFields) {
        if(page == null) {
            page = new Page();
        }
        t.getClass().getDeclaredFields();
//        t.getClass().getFields();
//        Example example = new Example(t.getClass());
        return null;
    }
}
