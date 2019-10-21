package com.rpa.web.mapper;

import com.rpa.web.domain.BatchInfoDO;
import com.rpa.web.pojo.BatchInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * BatchInfoMapper继承基类
 */
@Mapper
public interface BatchInfoMapper extends BaseDAO<BatchInfoDO, Integer> {
    void insertBatchInfo(List<BatchInfoPO> batchInfoPOs);
    void updateStatusByBatchId(Byte status, Integer batchId);

    /**
     * 根据用户Id查询数据
     * @param userId
     * @return
     */
    List<BatchInfoDO> queryByUserId(int userId);

    Integer queryStatusById(Integer batchId, int status);

    List<BatchInfoDO> queryByBatchid(Map<String, Object> map);
}