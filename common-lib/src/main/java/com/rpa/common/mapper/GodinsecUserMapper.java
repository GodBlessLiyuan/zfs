package com.rpa.common.mapper;

import com.rpa.common.pojo.GodinsecUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GodinsecUserMapper继承基类
 */
@Mapper
public interface GodinsecUserMapper extends BaseMapper<GodinsecUserPO, String> {
    List<GodinsecUserPO> queryByUserId(int userId);
}