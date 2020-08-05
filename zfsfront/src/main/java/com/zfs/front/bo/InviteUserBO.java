package com.zfs.front.bo;

import com.zfs.front.pojo.InviteUserPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/28 21:52
 * @description: 邀请详情
 * @version: 1.0
 */
@Data
public class InviteUserBO extends InviteUserPO {
    private Long earnings;
}
