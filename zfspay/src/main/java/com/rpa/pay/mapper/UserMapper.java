package com.rpa.pay.mapper;


import com.rpa.pay.bo.UserToBO;
import com.rpa.pay.pojo.UserPO;
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