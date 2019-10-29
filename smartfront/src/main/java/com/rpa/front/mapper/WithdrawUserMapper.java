package com.rpa.front.mapper;

import com.rpa.front.pojo.WithdrawUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * WithdrawUserMapper继承基类
 */
@Mapper
public interface WithdrawUserMapper extends BaseMapper<WithdrawUserPO, Integer> {

    /**
     * 根据UserId查询数据
     *
     * @param userId
     * @return
     */
    List<WithdrawUserPO> queryByUserId(long userId);
}