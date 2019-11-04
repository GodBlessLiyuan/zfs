package com.rpa.front.mapper;

import com.rpa.front.pojo.AppPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AppMapper继承基类
 */
@Mapper
public interface AppMapper extends BaseMapper<AppPO, Integer> {
    /**
     * 查询最新发布应用版本
     *
     * @return
     */
    AppPO queryLatestRelease();
}