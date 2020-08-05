package com.zfs.common.mapper;

import com.zfs.common.pojo.PhoneModelPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * PhoneModelMapper继承基类
 */
@Mapper
public interface PhoneModelMapper extends BaseMapper<PhoneModelPO, Long> {
}