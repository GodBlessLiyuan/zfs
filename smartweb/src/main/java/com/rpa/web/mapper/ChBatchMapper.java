package com.rpa.web.mapper;

import com.rpa.web.pojo.ChBatchPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ChBatchMapper继承基类
 */
@Mapper
public interface ChBatchMapper extends BaseDAO<ChBatchPO, Integer> {
}