package com.rpa.front.mapper;

import com.rpa.front.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper继承基类
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO, Long> {
    /**
     * 根据手机号查询
     *
     * @param phone
     * @return
     */
    UserPO queryByPhone(String phone);
}