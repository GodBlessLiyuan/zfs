package com.rpa.consumer.mapper;

import com.rpa.consumer.bo.InviteUserBO;
import com.rpa.consumer.pojo.InviteUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * InviteUserMapper继承基类
 */
@Mapper
public interface InviteUserMapper extends BaseMapper<InviteUserPO, Integer> {
    /**
     * 根据被邀请人Id 查询
     *
     * @param inviteeId
     * @return
     */
    InviteUserBO queryByInviteeId(Long inviteeId);
}