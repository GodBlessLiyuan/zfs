package com.rpa.web.mapper;

import com.rpa.web.pojo.BatchInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * BatchInfoMapper继承基类
 */
@Mapper
public interface BatchInfoMapper extends BaseDAO<BatchInfoPO, Integer> {
}