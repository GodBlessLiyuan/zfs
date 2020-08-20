package com.zfs.common.mapper;

import com.zfs.common.pojo.SoftChannelPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * SoftChannelMapper继承基类
 */
@Mapper
public interface SoftChannelMapper extends BaseMapper<SoftChannelPO, Integer> {
    Integer queryIdbyName(String chanName);

    List<Integer> queryIDS();
    //根据插件，找到渠道
    List<HashMap<String, Object>> queryPOSByIDS(Integer pluginId);

    List<Integer> queryIDSByIDS(Integer pluginId);

    List<String> queryNamesSByIDS(Integer pluginId);
}