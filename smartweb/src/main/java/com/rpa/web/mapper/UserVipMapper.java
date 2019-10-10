package com.rpa.web.mapper;

import com.rpa.web.domain.UserVipDO;
import com.rpa.web.pojo.UserVipPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserVipMapper继承基类
 */
@Mapper
public interface UserVipMapper extends BaseDAO<UserVipDO, UserVipPO> {
}