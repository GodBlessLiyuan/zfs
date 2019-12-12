package com.rpa.common.mapper;

import com.rpa.common.pojo.FunctionVideoPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * FunctionVideoMapper继承基类
 */
@Mapper
public interface FunctionVideoMapper extends BaseMapper<FunctionVideoPO, Integer> {
    String queryUsernameByAid(Integer aId);

    int queryFunname(String funName);

    String queryFunnameById(Integer functionId);
}