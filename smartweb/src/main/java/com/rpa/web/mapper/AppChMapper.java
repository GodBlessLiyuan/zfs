package com.rpa.web.mapper;

import com.rpa.web.pojo.AppChPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AppChMapper继承基类
 */
@Mapper
public interface AppChMapper extends BaseDAO<AppChPO, Integer> {
}