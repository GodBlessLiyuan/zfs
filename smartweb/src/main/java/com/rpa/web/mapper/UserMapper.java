package com.rpa.web.mapper;

import com.rpa.web.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper继承基类
 */
@Mapper
public interface UserMapper extends BaseDAO<UserPO, Long> {
}