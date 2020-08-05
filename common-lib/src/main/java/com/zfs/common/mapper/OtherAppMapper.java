package com.zfs.common.mapper;

import com.zfs.common.pojo.OtherAppPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * OtherAppMapper继承基类
 */
@Mapper
public interface OtherAppMapper extends BaseMapper<OtherAppPO, Integer> {
}