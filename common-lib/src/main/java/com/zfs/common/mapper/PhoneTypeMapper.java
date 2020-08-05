package com.zfs.common.mapper;

import com.zfs.common.pojo.PhoneTypePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * PhoneTypeMapper继承基类
 */
@Mapper
public interface PhoneTypeMapper extends BaseMapper<PhoneTypePO, Integer> {
}