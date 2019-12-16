package com.rpa.server.mapper;

import com.rpa.server.pojo.AdConfigPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AdConfigMapper继承基类
 */
@Mapper
public interface AdConfigMapper extends BaseMapper<AdConfigPO, Integer> {
    List<AdConfigPO> query(List<Integer> adIds);
}