package com.rpa.front.mapper;

import com.rpa.front.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper继承基类
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO, Long> {
}