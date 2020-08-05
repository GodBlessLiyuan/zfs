package com.zfs.common.mapper;

import com.zfs.common.pojo.FunctionvideoPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * FunctionVideoMapper继承基类
 */
@Mapper
public interface FunctionvideoMapper extends BaseMapper<FunctionvideoPO, Integer> {
    String queryUsernameByAid(Integer aId);

    int queryFunname(String funName);

    String queryFunnameById(Integer functionId);

    String queryUrl(String function);
}