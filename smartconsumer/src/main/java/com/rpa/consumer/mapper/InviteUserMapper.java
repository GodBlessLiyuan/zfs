package com.rpa.consumer.mapper;

import com.rpa.consumer.pojo.InviteUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * InviteUserMapper继承基类
 */
@Mapper
public interface InviteUserMapper extends BaseMapper<InviteUserPO, Integer> {
}