package com.rpa.web.mapper;

import com.rpa.web.pojo.KeyValuePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * KeyValueMapper继承基类
 */
@Mapper
public interface KeyValueMapper extends BaseDAO<KeyValuePO, Integer> {
    int updateStrategy(int showInterval, int show_interval);
}