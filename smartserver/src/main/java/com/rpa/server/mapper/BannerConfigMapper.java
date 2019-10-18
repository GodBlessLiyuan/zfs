package com.rpa.server.mapper;

import com.rpa.server.pojo.BannerConfigPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BannerConfigMapper继承基类
 */
@Mapper
public interface BannerConfigMapper extends BaseMapper<BannerConfigPO, Integer> {
    /**
     * 查询所有数据
     *
     * @return
     */
    List<BannerConfigPO> queryAll();
}