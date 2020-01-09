package com.rpa.common.mapper;

import com.rpa.common.pojo.PhoneTypePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * PhoneTypeMapper继承基类
 */
@Mapper
public interface PhoneTypeMapper extends BaseMapper<PhoneTypePO, Integer> {
}