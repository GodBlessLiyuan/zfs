package com.zfs.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.zfs.common.pojo.ViptypePO;

/**
 * ViptypeMapper继承基类
 */
@Mapper
public interface ViptypeMapper extends BaseMapper<ViptypePO, Integer> {
    String queryVipnameByVipid(Integer viptypeId);

    ViptypePO queryName(String vipname);
}