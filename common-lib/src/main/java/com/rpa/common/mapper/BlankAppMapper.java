package com.rpa.common.mapper;

import com.rpa.common.pojo.BlankAppPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BlankAppMapper继承基类
 */
@Mapper
public interface BlankAppMapper extends BaseMapper<BlankAppPO, Long> {
    /**
     * 查询id大于bid的数据
     *
     * @param bid
     * @return
     */
    List<BlankAppPO> queryById(Long bid);
}