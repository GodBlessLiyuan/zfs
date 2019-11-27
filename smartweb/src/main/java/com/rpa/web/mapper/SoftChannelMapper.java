package com.rpa.web.mapper;

import com.rpa.web.pojo.SoftChannelPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * SoftChannelMapper继承基类
 */
@Mapper
public interface SoftChannelMapper extends BaseDAO<SoftChannelPO, Integer> {
    String queryNameById(Integer softChannelId);

    List<String> queryNames();
}