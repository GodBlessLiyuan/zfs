package com.rpa.common.mapper;

import com.rpa.common.pojo.SoftChannelPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SoftChannelMapper继承基类
 */
@Mapper
public interface SoftChannelMapper extends BaseMapper<SoftChannelPO, Integer> {
    Integer queryIdbyName(String chanName);

    List<Integer> queryIDS();
}