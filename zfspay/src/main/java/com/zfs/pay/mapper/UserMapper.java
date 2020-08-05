package com.zfs.pay.mapper;


import com.zfs.pay.bo.UserToBO;
import com.zfs.pay.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;

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

    UserToBO selPri(Long userId);
}