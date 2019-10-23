package com.rpa.web.mapper;

import com.rpa.web.pojo.VipTypePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ViptypeMapper继承基类
 */
@Mapper
public interface VipTypeMapper extends BaseDAO<VipTypePO, Integer> {
    String queryVipnameByVipid(Integer viptypeId);
}