package com.rpa.web.mapper;

import com.rpa.web.pojo.OtherAppPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * OtherAppMapper继承基类
 */
@Mapper
public interface OtherAppMapper extends BaseDAO<OtherAppPO, Integer> {
}