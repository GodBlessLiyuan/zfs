package com.rpa.web.mapper;

import com.rpa.web.pojo.WithdrawUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * WithdrawUserMapper继承基类
 */
@Mapper
public interface WithdrawUserMapper extends BaseDAO<WithdrawUserPO, Integer> {
}