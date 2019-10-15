package com.rpa.server.mapper;

import com.rpa.server.pojo.UserHistoryPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserHistoryMapper继承基类
 */
@Mapper
public interface UserHistoryMapper extends BaseMapper<UserHistoryPO, Integer> {
}