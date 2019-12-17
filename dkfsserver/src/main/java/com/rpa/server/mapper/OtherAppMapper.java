package com.rpa.server.mapper;

import com.rpa.common.pojo.OtherAppPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OtherAppMapper继承基类
 */
@Mapper
public interface OtherAppMapper extends BaseMapper<OtherAppPO, Integer> {
    /**
     * 查询所有其他应用
     * @return
     */
    List<OtherAppPO> queryAll();
}