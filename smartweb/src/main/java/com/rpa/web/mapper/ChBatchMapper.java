package com.rpa.web.mapper;

import com.rpa.web.pojo.ChBatch;
import org.apache.ibatis.annotations.Mapper;

/**
 * ChBatchMapper继承基类
 */
@Mapper
public interface ChBatchMapper extends BaseDAO<ChBatch, Integer> {
}