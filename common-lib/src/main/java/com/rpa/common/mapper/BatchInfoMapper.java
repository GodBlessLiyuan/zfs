package com.rpa.common.mapper;

import com.rpa.common.pojo.BatchInfoPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * BatchInfoMapper继承基类
 */
@Mapper
public interface BatchInfoMapper extends BaseMapper<BatchInfoPO, Integer> {
}