package com.zfs.rabbit.mapper;

import com.zfs.rabbit.bo.InviteUserBO;
import com.zfs.rabbit.pojo.InviteUserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 根据被邀请人手机号 查询
     * @param phone
     * @return
     */
    List<InviteUserPO> queryByPhone(String phone);
}