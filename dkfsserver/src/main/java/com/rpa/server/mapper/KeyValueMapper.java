package com.rpa.server.mapper;

import com.rpa.server.pojo.KeyValuePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * KeyValueMapper继承基类
 */
@Mapper
public interface KeyValueMapper extends BaseMapper<KeyValuePO, Integer> {
    String queryValue(int showInterval);
}