package com.rpa.server.mapper;

import com.rpa.server.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper继承基类
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO, Long> {
    /**
     * 根据phone查询数据
     * @param ph
     * @return
     */
    UserPO queryByPhone(String ph);
}