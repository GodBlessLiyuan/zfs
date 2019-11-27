package com.rpa.web.mapper;

import com.rpa.web.pojo.GodinsecUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GodinsecUserMapper继承基类
 */
@Mapper
public interface GodinsecUserMapper extends BaseDAO<GodinsecUserPO, String> {
    List<GodinsecUserPO> queryByUserId(int userId);
}