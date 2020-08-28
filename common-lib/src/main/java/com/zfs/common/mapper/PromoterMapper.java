package com.zfs.common.mapper;

import com.zfs.common.pojo.PromoterPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * PromoterMapper继承基类
 */
@Mapper
public interface PromoterMapper extends BaseMapper<PromoterPO, Integer> {
    List<PromoterPO> queryAllProNames();

    List<PromoterPO> queryProName(String proName);
}