package com.rpa.common.mapper;

import com.rpa.common.pojo.BlackAppPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BlackAppMapper继承基类
 */
@Mapper
public interface BlackAppMapper extends BaseMapper<BlackAppPO, Long> {
    /**
     * 查询id大于bid的数据
     *
     * @param bid
     * @return
     */
    List<BlackAppPO> queryById(Long bid);
}