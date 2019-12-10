package com.rpa.common.mapper;

import com.rpa.common.pojo.UserHistoryPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserHistoryMapper继承基类
 */
@Mapper
public interface UserHistoryMapper extends BaseMapper<UserHistoryPO, Integer> {
}