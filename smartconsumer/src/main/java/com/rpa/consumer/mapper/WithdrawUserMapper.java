package com.rpa.consumer.mapper;

import com.rpa.consumer.pojo.WithdrawUserPO;
import org.springframework.web.bind.annotation.Mapping;

/**
 * WithdrawUserMapper继承基类
 */
@Mapping
public interface WithdrawUserMapper extends BaseMapper<WithdrawUserPO, Integer> {
}