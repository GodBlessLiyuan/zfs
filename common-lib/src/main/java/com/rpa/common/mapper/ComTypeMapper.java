package com.rpa.common.mapper;

import com.rpa.common.pojo.ComTypePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ComTypeMapper继承基类
 */
@Mapper
public interface ComTypeMapper extends BaseMapper<ComTypePO, Integer> {
    String queryTypenameByTypeid(Integer comTypeId);

    String queryNameDays(Integer days);
}