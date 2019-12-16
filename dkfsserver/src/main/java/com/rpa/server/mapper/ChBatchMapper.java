package com.rpa.server.mapper;

import com.rpa.server.pojo.ChBatchPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ChBatchMapper继承基类
 */
@Mapper
public interface ChBatchMapper extends BaseMapper<ChBatchPO, Integer> {
}