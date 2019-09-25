package com.rpa.web.mapper;

import com.rpa.web.pojo.ComTypePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ComTypeMapper继承基类
 */
@Mapper
public interface ComTypeMapper extends BaseDAO<ComTypePO, Integer> {

}