package com.rpa.web.mapper;

import com.rpa.web.pojo.InviteUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * InviteUserMapper继承基类
 */
@Mapper
public interface InviteUserMapper extends BaseDAO<InviteUserPO, Integer> {
}