package com.rpa.server.bo;

import com.rpa.server.pojo.NewUserRecordPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/24 19:38
 * @description: 新用户赠送
 * @version: 1.0
 */
@Data
public class NewUserRecordBO extends NewUserRecordPO {
    private String comTypeName;
}
