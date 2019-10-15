package com.rpa.server.mapper;

import com.rpa.server.pojo.BannerConfigPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * BannerConfigMapper继承基类
 */
@Mapper
public interface BannerConfigMapper extends BaseMapper<BannerConfigPO, Integer> {
}