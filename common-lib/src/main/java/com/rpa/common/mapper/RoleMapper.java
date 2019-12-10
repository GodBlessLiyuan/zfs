package com.rpa.common.mapper;

import com.rpa.common.pojo.RolePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * RoleMapper继承基类
 */
@Mapper
public interface RoleMapper extends BaseMapper<RolePO, Integer> {
}