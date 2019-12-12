package com.rpa.common.mapper;

import com.rpa.common.bo.BatchInfoBO;
import com.rpa.common.pojo.BatchInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * BatchInfoMapper继承基类
 */
@Mapper
public interface BatchInfoMapper extends BaseMapper<BatchInfoBO, Integer> {
    void insertBatchInfo(List<BatchInfoPO> batchInfoPOs);
    void updateStatusByBatchId(Byte status, Integer batchId);

    /**
     * 根据用户Id查询数据
     * @param userId
     * @return
     */
    List<BatchInfoBO> queryByUserId(int userId);

    Integer queryStatusById(@Param("batchId")int batchId, @Param("status")int status);

    List<BatchInfoBO> queryByBatchid(Map<String, Object> map);
}