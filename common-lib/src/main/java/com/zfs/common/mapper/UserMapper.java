package com.zfs.common.mapper;

import com.zfs.common.bo.UserBO;
import com.zfs.common.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserMapper继承基类
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO, Long> {
    String queryPhoneByUserId(Long userId);

    int queryTodayNewRegister();

    /**
     * 根据phone查询数据
     *
     * @param ph
     * @return
     */
    UserPO queryByPhone(String ph);

    List<UserBO> selUserDeivce();
}