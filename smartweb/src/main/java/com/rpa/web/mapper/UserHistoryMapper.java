package com.rpa.web.mapper;

import com.rpa.web.pojo.UserHistoryPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserHistoryMapper继承基类
 */
@Mapper
public interface UserHistoryMapper extends BaseDAO<UserHistoryPO, Integer> {
}