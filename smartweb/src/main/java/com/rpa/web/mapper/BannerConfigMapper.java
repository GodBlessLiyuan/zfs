package com.rpa.web.mapper;

import com.rpa.web.pojo.BannerConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * BannerConfigMapper继承基类
 */
@Mapper
public interface BannerConfigMapper extends BaseDAO<BannerConfig, Integer> {
}