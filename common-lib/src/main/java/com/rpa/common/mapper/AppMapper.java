package com.rpa.common.mapper;

import com.rpa.common.pojo.AppPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AppMapper继承基类
 */
@Mapper
public interface AppMapper extends BaseMapper<AppPO, Integer> {
}