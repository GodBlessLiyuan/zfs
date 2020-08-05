package com.zfs.common.mapper;

import com.zfs.common.pojo.AdconfigPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * AdconfigMapper继承基类
 */
@Mapper
public interface AdconfigMapper extends BaseMapper<AdconfigPO, Integer> {
    List<AdconfigPO> query(Map<String, Object> map);
    List<AdconfigPO> queryById(List<Integer> adIds);

    List<Integer> queryIDS();
}