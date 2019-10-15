package com.rpa.server.mapper;

import com.rpa.server.pojo.InviteUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * InviteUserMapper继承基类
 */
@Mapper
public interface InviteUserMapper extends BaseMapper<InviteUserPO, Integer> {
}