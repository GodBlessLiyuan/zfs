package com.rpa.server.mapper;

import com.rpa.server.pojo.WxSupportPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * WxSupportMapper继承基类
 */
@Mapper
public interface WxSupportMapper extends BaseMapper<WxSupportPO, Integer> {
    int queryMaxId();

    List<String> queryPkgs();
}