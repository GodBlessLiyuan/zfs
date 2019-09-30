package com.rpa.web.mapper;

import com.rpa.web.pojo.AppPluChPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AppPluChMapper继承基类
 */
@Mapper
public interface AppPluChMapper extends BaseDAO<AppPluChPO, Integer> {
}