package com.rpa.web.mapper;

import com.rpa.web.pojo.RolePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RoleMapper继承基类
 */
@Mapper
public interface RoleMapper extends BaseDAO<RolePO, Integer> {
    List<RolePO> queryAllRoles();

    List<RolePO> query();
}