package com.zfs.common.mapper;

import com.zfs.common.pojo.AdminLogPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AdminLogMapper继承基类
 */
@Mapper
public interface AdminLogMapper extends BaseMapper<AdminLogPO, Integer> {
}