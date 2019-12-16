package com.rpa.server.mapper;

import com.rpa.server.pojo.UserVipPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserVipMapper继承基类
 */
@Mapper
public interface UserVipMapper extends BaseMapper<UserVipPO, UserVipPO> {
    /**
     * 根据用户Id查询数据
     *
     * @param ud 用户Id
     * @return
     */
    UserVipPO queryByUserId(Long ud);
}