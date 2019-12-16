package com.rpa.server.mapper;

import com.rpa.server.pojo.WithdrawUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WithdrawUserMapper继承基类
 */
@Mapper
public interface WithdrawUserMapper extends BaseMapper<WithdrawUserPO, Integer> {
}