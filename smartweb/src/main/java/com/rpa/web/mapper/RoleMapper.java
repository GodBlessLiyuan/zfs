package com.rpa.web.mapper;

import com.rpa.web.pojo.RolePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * RoleMapper继承基类
 */
@Mapper
public interface RoleMapper extends BaseDAO<RolePO, Integer> {
}