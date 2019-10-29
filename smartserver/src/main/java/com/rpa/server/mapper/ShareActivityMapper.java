package com.rpa.server.mapper;

import com.rpa.server.pojo.ShareActivityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ShareActivityMapper继承基类
 */
@Mapper
public interface ShareActivityMapper extends BaseMapper<ShareActivityPO, Integer> {
    List<ShareActivityPO> query();
}