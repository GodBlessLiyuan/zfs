package com.rpa.server.mapper;

import com.rpa.server.bo.BatchInfoBO;
import com.rpa.server.pojo.BatchInfoPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BatchInfoMapper继承基类
 */
@Mapper
public interface BatchInfoMapper extends BaseMapper<BatchInfoPO, Integer> {
    /**
     * 根据userId查询卡密信息
     * @param ud
     * @return
     */
    List<BatchInfoBO> queryByUserId(Long ud);

    /**
     * 根据userId和key查询卡密信息
     * @param userId
     * @param key
     * @return
     */
    BatchInfoPO queryByUserIdAndKey(Long userId, String key);
}