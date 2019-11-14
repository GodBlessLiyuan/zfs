package com.rpa.rabbit.bo;

import com.rpa.rabbit.pojo.InviteUserPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/11/8 9:09
 * @description: 用户邀请详情
 * @version: 1.0
 */
@Data
public class InviteUserBO extends InviteUserPO {
    private Long inviteeId;
    private Integer vipTypeId;
}
