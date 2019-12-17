package com.rpa.common.mapper;

import com.rpa.common.pojo.GodinsecUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GodinsecUserMapper继承基类
 */
@Mapper
public interface GodinsecUserMapper extends BaseMapper<GodinsecUserPO, String> {
    List<GodinsecUserPO> queryByUserId(Long userId);

    /**
     * 根据用户名查询
     *
     * @param userId
     * @return
     */
    List<GodinsecUserPO> queryByUserId2(Long userId);
}