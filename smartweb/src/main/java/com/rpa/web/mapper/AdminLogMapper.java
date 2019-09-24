package com.rpa.web.mapper;

import com.rpa.web.pojo.AdminLogPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AdminLogMapper继承基类
 */
@Mapper
public interface AdminLogMapper extends BaseDAO<AdminLogPO, Integer> {
}