package com.rpa.server.mapper;

import com.rpa.server.pojo.NewUserRecordPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * NewUserRecordMapper继承基类
 */
@Mapper
public interface NewUserRecordMapper extends BaseMapper<NewUserRecordPO, Integer> {
}