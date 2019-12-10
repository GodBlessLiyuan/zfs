package com.rpa.common.mapper;

import com.rpa.common.pojo.WhiteUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WhiteUserMapper继承基类
 */
@Mapper
public interface WhiteUserMapper extends BaseMapper<WhiteUserPO, WhiteUserPO> {
}