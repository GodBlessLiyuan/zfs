package com.zfs.common.mapper;

import com.zfs.common.bo.BatchInfoBO;
import com.zfs.common.pojo.BatchInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * BatchInfoMapper继承基类
 */
@Mapper
public interface BatchInfoMapper extends BaseMapper<BatchInfoPO, Integer> {
    int insertBatchInfo(List<BatchInfoPO> batchInfoPOs);
    int updateStatusByBatchId(@Param("status")Byte status, @Param("batchId")Integer batchId);

    /**
     * 根据用户Id查询数据
     * @param userId
     * @return
     */
    List<BatchInfoBO> queryByUserId(Long userId);
    //统计批次下状态的总数
    Integer queryStatusById(@Param("batchId")int batchId, @Param("status")int status);

    List<BatchInfoBO> queryByBatchid(Map<String, Object> map);

    /**
     * 根据userId查询卡密信息
     *
     * @param ud
     * @return
     */
    List<BatchInfoBO> queryByUserId2(Long ud);

    /**
     * 根据key查询卡密信息
     *
     * @param key
     * @return
     */
    BatchInfoPO queryByKey(String key);
    //查询批次下的状态值的主键
    List<Integer> queryStatus(@Param("batchId") Integer batchId,@Param("originStatu") Byte originStatu);
}