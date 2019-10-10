package com.rpa.web.mapper;

import com.rpa.web.domain.BatchInfoDO;
import com.rpa.web.pojo.BatchInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BatchInfoMapper继承基类
 */
@Mapper
public interface BatchInfoMapper extends BaseDAO<BatchInfoDO, Integer> {
    void insertBatchInfo(List<BatchInfoPO> batchInfoPOs);
    void updateStatusByBatchId(Byte status, Integer batchId);
}