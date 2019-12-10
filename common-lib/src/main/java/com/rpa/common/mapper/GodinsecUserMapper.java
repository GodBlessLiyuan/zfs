package com.rpa.common.mapper;

import com.rpa.common.pojo.GodinsecUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * GodinsecUserMapper继承基类
 */
@Mapper
public interface GodinsecUserMapper extends BaseMapper<GodinsecUserPO, String> {
}