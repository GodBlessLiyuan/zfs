package com.zfs.common.mapper;

import com.zfs.common.pojo.ComTypePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ComTypeMapper继承基类
 */
@Mapper
public interface ComTypeMapper extends BaseMapper<ComTypePO, Integer> {
    String queryTypenameByTypeid(Integer comTypeId);

    String queryNameDays(Integer days);

    Integer exist(@Param("name") String name,@Param("days") int days);
}