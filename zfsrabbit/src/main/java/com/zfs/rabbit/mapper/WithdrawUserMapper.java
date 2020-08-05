package com.zfs.rabbit.mapper;

import com.zfs.rabbit.pojo.WithdrawUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WithdrawUserMapper继承基类
 */
@Mapper
public interface WithdrawUserMapper extends BaseMapper<WithdrawUserPO, Integer> {
}