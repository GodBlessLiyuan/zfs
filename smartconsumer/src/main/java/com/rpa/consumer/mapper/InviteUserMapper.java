package com.rpa.consumer.mapper;

import com.rpa.consumer.pojo.InviteUserPO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

/**
 * InviteUserMapper继承基类
 */
@Mapping
public interface InviteUserMapper extends BaseMapper<InviteUserPO, Integer> {
}