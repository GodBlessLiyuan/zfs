package com.rpa.server.mapper;

import com.rpa.server.pojo.GodinsecUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * GodinsecUserMapper继承基类
 */
@Mapper
public interface GodinsecUserMapper extends BaseMapper<GodinsecUserPO, GodinsecUserPO> {
}