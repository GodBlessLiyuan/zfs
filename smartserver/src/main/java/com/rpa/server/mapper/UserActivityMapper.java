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
     * 根据 userId 查询活动记录
     * @param userId
     * @return
     */
    List<UserActivityBO> queryByUserId(Long userId);
}