package com.rpa.server.bo;

import com.rpa.server.pojo.UserActivityPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/24 19:35
 * @description: 活动记录
 * @version: 1.0
 */
@Data
public class UserActivityBO extends UserActivityPO {
    private String comTypeName;
}
