package com.rpa.common.mapper;

import com.rpa.common.pojo.AppPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AppMapper继承基类
 */
@Mapper
public interface AppMapper extends BaseMapper<AppPO, Integer> {
    List<Integer> queryVersioncodes();
}