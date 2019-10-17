package com.rpa.web.mapper;

import com.rpa.web.pojo.AppPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AppMapper继承基类
 */
@Mapper
public interface AppMapper extends BaseDAO<AppPO, Integer> {
    /**
     * 根据Id查询数据
     * @param appId
     * @return
     */
    List<AppPO> queryById(int appId);
}