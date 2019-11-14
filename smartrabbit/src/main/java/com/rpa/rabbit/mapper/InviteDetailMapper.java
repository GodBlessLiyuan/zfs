package com.rpa.rabbit.mapper;

import com.rpa.rabbit.pojo.InviteDetailPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * InviteDetailMapper继承基类
 */
@Mapper
public interface InviteDetailMapper extends BaseMapper<InviteDetailPO, Long> {
    /**
     * 查询注册人数
     * @param userId
     * @return
     */
    Long queryCountByUserId(Long userId);
}