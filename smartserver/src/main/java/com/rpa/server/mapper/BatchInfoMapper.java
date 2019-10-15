package com.rpa.server.mapper;

import com.rpa.server.pojo.BatchInfoPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * BatchInfoMapper继承基类
 */
@Mapper
public interface BatchInfoMapper extends BaseMapper<BatchInfoPO, Integer> {
}