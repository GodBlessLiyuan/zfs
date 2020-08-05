package com.zfs.common.bo;

import com.zfs.common.pojo.UserActivityPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/11 15:23
 * @description: TODO
 * @version: 1.0
 */
@Data
public class UserActivityBO extends UserActivityPO {
    private String phone;
    private String userChanName;
    /**
     * 日卡，周卡，月卡，年卡
     */
    private String comTypeName;
    private Integer days;
    /**
     * 1 活动赠送
     */
    private Integer source;
}
