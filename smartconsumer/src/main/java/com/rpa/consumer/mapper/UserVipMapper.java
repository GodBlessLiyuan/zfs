package com.rpa.consumer.mapper;

import com.rpa.consumer.pojo.UserVipPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserVipMapper继承基类
 */
@Mapper
public interface UserVipMapper extends BaseMapper<UserVipPO, UserVipPO> {
}