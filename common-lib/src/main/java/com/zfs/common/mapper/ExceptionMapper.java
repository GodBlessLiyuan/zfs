package com.zfs.common.mapper;

import com.zfs.common.pojo.ExceptionPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ExceptionMapper继承基类
 */
@Mapper
public interface ExceptionMapper extends BaseMapper<ExceptionPO, Integer> {
}