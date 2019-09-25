package com.rpa.web.mapper;

import com.rpa.web.pojo.AppPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AppMapper继承基类
 */
@Mapper
public interface AppMapper extends BaseDAO<AppPO, Integer> {
}