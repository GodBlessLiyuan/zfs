package com.zfs.common.mapper;

import com.zfs.common.pojo.WxsupportPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * WxsupportMapper继承基类
 */
@Mapper
public interface WxsupportMapper extends BaseMapper<WxsupportPO, Integer> {
    int queryMaxId();
    List<String> queryPkgs();
}