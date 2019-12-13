package com.rpa.common.mapper;

import com.rpa.common.pojo.AppAvaChPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AppAvaChMapper继承基类
 */
@Mapper
public interface AppAvaChMapper extends BaseMapper<AppAvaChPO, Long> {
    void batchInsert(List<AppAvaChPO> aacPOs);
}