package com.rpa.common.bo;

import com.rpa.common.pojo.NewUserRecordPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/11 15:01
 * @description: TODO
 * @version: 1.0
 */
@Data
public class NewUserRecordBO extends NewUserRecordPO {
    private String phone;
    private String comTypeName;
    private Integer days;
    private String userChanName;
}
