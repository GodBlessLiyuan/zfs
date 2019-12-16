package com.rpa.server.mapper;

import com.rpa.server.pojo.WhiteUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WhiteUserMapper继承基类
 */
@Mapper
public interface WhiteUserMapper extends BaseMapper<WhiteUserPO, WhiteUserPO> {
}