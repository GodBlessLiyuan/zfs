package com.zfs.common.mapper;

import com.zfs.common.pojo.KeyValuePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * KeyValueMapper继承基类
 */
@Mapper
public interface KeyValueMapper extends BaseMapper<KeyValuePO, Integer> {
    String queryValue(int showInterval);
}