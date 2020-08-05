package com.zfs.common.mapper;

import com.zfs.common.pojo.ChBatchPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ChBatchMapper继承基类
 */
@Mapper
public interface ChBatchMapper extends BaseMapper<ChBatchPO, Integer> {

    Integer queryTypeIdByBatchId(Integer batchId);

    Integer queryDaysByTypeId(Integer typeId);

    Byte queryActiveByPri(Integer batchId);
}