package com.rpa.common.mapper;

import com.rpa.common.pojo.ChBatchPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ChBatchMapper继承基类
 */
@Mapper
public interface ChBatchMapper extends BaseMapper<ChBatchPO, Integer> {
}