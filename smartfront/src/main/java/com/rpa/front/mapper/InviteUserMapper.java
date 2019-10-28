package com.rpa.front.mapper;

import com.rpa.front.bo.InviteUserBO;
import com.rpa.front.pojo.InviteUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * InviteUserMapper继承基类
 */
@Mapper
public interface InviteUserMapper extends BaseMapper<InviteUserPO, Integer> {
    /**
     * 查询用户邀请详情
     *
     * @param userId
     * @return
     */
    List<InviteUserBO> queryByUserId(long userId);
}