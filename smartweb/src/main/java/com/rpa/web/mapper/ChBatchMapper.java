package com.rpa.web.mapper;

import com.rpa.web.pojo.BatchInfoPO;
import com.rpa.web.pojo.ChBatchPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ChBatchMapper继承基类
 */
@Mapper
public interface ChBatchMapper extends BaseDAO<ChBatchPO, Integer> {

    Integer queryTypeIdByBatchId(Integer batchId);

    Integer queryDaysByTypeId(Integer typeId);

}