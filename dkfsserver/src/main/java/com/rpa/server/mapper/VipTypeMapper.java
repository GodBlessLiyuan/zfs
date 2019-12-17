package com.rpa.server.mapper;

import com.rpa.common.pojo.ViptypePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * VipTypeMapper继承基类
 */
@Mapper
public interface VipTypeMapper extends BaseMapper<ViptypePO, Integer> {
}