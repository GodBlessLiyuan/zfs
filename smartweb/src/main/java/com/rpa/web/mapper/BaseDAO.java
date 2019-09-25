package com.rpa.web.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.rpa.web.pojo.ComTypePO;
import org.apache.ibatis.annotations.Param;

/**
 * DAO公共基类，由MybatisGenerator自动生成请勿修改
 * @param <Model> The Model Class 这里是泛型不是Model类
 * @param <PK> The Primary Key Class 如果是无主键，则可以用Model来跳过，如果是多主键则是Key类
 */
public interface BaseDAO<Model, PK extends Serializable> {
    int deleteByPrimaryKey(PK id);

    int insert(Model record);

    int insertSelective(Model record);

    Model selectByPrimaryKey(PK id);

    List<Model> query(Map<String, Object> map);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);
}