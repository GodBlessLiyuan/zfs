package com.rpa.common.mapper;

import com.rpa.common.pojo.WithdrawUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WithdrawUserMapper继承基类
 */
@Mapper
public interface WithdrawUserMapper extends BaseMapper<WithdrawUserPO, Integer> {
}