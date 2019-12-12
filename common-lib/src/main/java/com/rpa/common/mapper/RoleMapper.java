package com.rpa.common.mapper;

import com.rpa.common.pojo.RolePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RoleMapper继承基类
 */
@Mapper
public interface RoleMapper extends BaseMapper<RolePO, Integer> {
    List<RolePO> queryAllRoles();
    List<RolePO> query();
}