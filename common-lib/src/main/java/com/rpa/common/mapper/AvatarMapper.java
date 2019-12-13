package com.rpa.common.mapper;

import com.rpa.common.pojo.AvatarPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AvatarMapper继承基类
 */
@Mapper
public interface AvatarMapper extends BaseMapper<AvatarPO, Long> {
}