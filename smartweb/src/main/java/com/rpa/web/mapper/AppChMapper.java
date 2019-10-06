package com.rpa.web.mapper;

import com.rpa.web.pojo.AppChPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AppChMapper继承基类
 */
@Mapper
public interface AppChMapper extends BaseDAO<AppChPO, Integer> {

    /**
     * 批量插入
     * @param records 记录集
     * @return
     */
    int batchInsert(List<AppChPO> records);
}