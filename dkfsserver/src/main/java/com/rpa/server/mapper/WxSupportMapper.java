package com.rpa.server.mapper;

import com.rpa.common.pojo.WxsupportPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * WxSupportMapper继承基类
 */
@Mapper
public interface WxSupportMapper extends BaseMapper<WxsupportPO, Integer> {
    int queryMaxId();

    List<String> queryPkgs();
}