package com.rpa.server.mapper;

import com.rpa.server.pojo.GodinsecUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GodinsecUserMapper继承基类
 */
@Mapper
public interface GodinsecUserMapper extends BaseMapper<GodinsecUserPO, String> {
    /**
     * 根据用户名查询
     *
     * @param userId
     * @return
     */
    List<GodinsecUserPO> queryByUserId(Long userId);
}