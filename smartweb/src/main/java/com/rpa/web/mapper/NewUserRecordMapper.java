package com.rpa.web.mapper;

import com.rpa.web.pojo.NewUserRecordPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * NewUserRecordMapper继承基类
 */
@Mapper
public interface NewUserRecordMapper extends BaseDAO<NewUserRecordPO, Integer> {
}