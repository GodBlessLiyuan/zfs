package com.rpa.server.mapper;

import com.rpa.common.pojo.UserActivityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserActivityMapper继承基类
 */
@Mapper
public interface UserActivityMapper extends BaseMapper<UserActivityPO, Integer> {
    /**
     * 根据 userId 查询所有已激活的活动记录
     *
     * @param userId
     * @return
     */
    List<UserActivityPO> queryActivatedByUserId(Long userId);

    /**
     * 通过 userId 和 status 查询 (status == 0 时，查询所有状态)
     *
     * @param userId
     * @param status
     * @return
     */
    List<UserActivityPO> queryByUserIdAndStatus(Long userId, Byte status);

    /**
     * 激活
     *
     * @param userId
     */
    void activate(Long userId);


}