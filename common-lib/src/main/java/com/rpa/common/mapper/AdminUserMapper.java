package com.rpa.common.mapper;

import com.rpa.common.pojo.AdminUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AdminUserMapper继承基类
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUserPO, Integer> {
}