package com.zfs.pay.mapper;

import com.zfs.pay.pojo.UserVipPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserVipMapper继承基类
 */
@Mapper
public interface UserVipMapper extends BaseMapper<UserVipPO, UserVipPO> {
    /**
     * 根据用户Id查询数据
     *
     * @param userId 用户Id
     * @return
     */
    UserVipPO queryByUserId(Long userId);
}