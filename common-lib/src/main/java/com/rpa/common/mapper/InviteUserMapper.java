package com.rpa.common.mapper;

import com.rpa.common.pojo.InviteUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * InviteUserMapper继承基类
 */
@Mapper
public interface InviteUserMapper extends BaseMapper<InviteUserPO, Integer> {
}