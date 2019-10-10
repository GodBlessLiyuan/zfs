package com.rpa.web.mapper;

import com.rpa.web.pojo.BannerConfigPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * BannerConfigMapper继承基类
 */
@Mapper
public interface BannerConfigMapper extends BaseDAO<BannerConfigPO, Integer> {
    String queryUsernameByAid(Integer aId);
}