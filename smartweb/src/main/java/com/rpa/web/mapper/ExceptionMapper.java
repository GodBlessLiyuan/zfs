package com.rpa.web.mapper;

import com.rpa.web.pojo.ExceptionPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ExceptionMapper继承基类
 */
@Mapper
public interface ExceptionMapper extends BaseDAO<ExceptionPO, Integer> {
}