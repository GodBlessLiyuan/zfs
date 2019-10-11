package com.rpa.web.domain;

import com.rpa.web.pojo.NewUserRecordPO;
import lombok.ToString;

/**
 * @author: xiahui
 * @date: Created in 2019/10/10 9:04
 * @description: TODO
 * @version: 1.0
 */
@ToString
public class NewUserRecordDO extends NewUserRecordPO {

    private String userChanName;

    public String getUserChanName() {
        return userChanName;
    }

    public void setUserChanName(String userChanName) {
        this.userChanName = userChanName;
    }
}
