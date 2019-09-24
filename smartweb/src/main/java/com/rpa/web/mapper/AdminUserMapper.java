package com.rpa.web.mapper;

import com.rpa.web.pojo.AdminUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AdminUserMapper继承基类
 */
@Mapper
public interface AdminUserMapper extends BaseDAO<AdminUserPO, Integer> {
}