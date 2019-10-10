package com.rpa.web.mapper;

import com.rpa.web.domain.UserActivityDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserActivityMapper继承基类
 */
@Mapper
public interface UserActivityMapper extends BaseDAO<UserActivityDO, Integer> {
    /**
     * 根据userId查询数据
     * @param userId
     * @return
     */
    List<UserActivityDO> queryByUserId(int userId);
}