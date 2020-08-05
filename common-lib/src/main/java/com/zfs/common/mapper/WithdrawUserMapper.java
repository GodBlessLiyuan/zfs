package com.zfs.common.mapper;

import com.zfs.common.pojo.WithdrawUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WithdrawUserMapper继承基类
 */
@Mapper
public interface WithdrawUserMapper extends BaseMapper<WithdrawUserPO, Integer> {
    String queryUsernameByAid(Integer aId);
}