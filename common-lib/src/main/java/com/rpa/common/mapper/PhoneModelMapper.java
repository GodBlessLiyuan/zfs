package com.rpa.common.mapper;

import com.rpa.common.pojo.PhoneModelPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * PhoneModelMapper继承基类
 */
@Mapper
public interface PhoneModelMapper extends BaseMapper<PhoneModelPO, Long> {
}