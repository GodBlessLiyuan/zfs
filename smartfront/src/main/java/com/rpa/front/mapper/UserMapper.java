package com.rpa.front.mapper;

import com.rpa.front.pojo.UserPO;
import org.springframework.stereotype.Repository;

/**
 * UserMapper继承基类
 */
@Repository
public interface UserMapper extends BaseMapper<UserPO, Long> {
}