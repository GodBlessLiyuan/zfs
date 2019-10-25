package com.rpa.server.mapper;

import com.rpa.server.bo.UserActivityBO;
import com.rpa.server.pojo.UserActivityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserActivityMapper继承基类
 */
@Mapper
public interface UserActivityMapper extends BaseMapper<UserActivityPO, Integer> {
    /**
     * 根据 userId 查询所有已激活的活动记录
     * @param userId
     * @return
     */
    List<UserActivityBO> queryActivatedByUserId(Long userId);

    /**
     * 根据 userId 查询所有已通过的活动记录
     * @param userId
     * @return
     */
    List<UserActivityPO> queryPassedByUserId(Long userId);

    /**
     * 激活
     * @param userId
     */
    void activate(Long userId);
}