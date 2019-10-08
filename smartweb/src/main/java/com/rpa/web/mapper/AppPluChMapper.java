package com.rpa.web.mapper;

import com.rpa.web.pojo.AppPluChPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AppPluChMapper继承基类
 */
@Mapper
public interface AppPluChMapper extends BaseDAO<AppPluChPO, Integer> {

    /**
     * 批量插入
     *
     * @param records 记录集
     * @return
     */
    int batchInsert(List<AppPluChPO> records);
}