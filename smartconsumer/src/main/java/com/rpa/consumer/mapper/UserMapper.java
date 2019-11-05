package com.rpa.consumer.mapper;

import com.rpa.consumer.pojo.UserPO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

/**
 * UserMapper继承基类
 */
@Mapping
public interface UserMapper extends BaseMapper<UserPO, Long> {
}