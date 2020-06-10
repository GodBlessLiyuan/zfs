package com.rpa.common.mapper;

import com.rpa.common.pojo.RegisterUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * RegisterUserMapper继承基类
 */
@Mapper
public interface RegisterUserMapper extends BaseMapper<RegisterUserPO, Long> {
}