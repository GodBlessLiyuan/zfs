package com.rpa.common.bo;

import com.rpa.common.pojo.UserPO;
import com.rpa.common.pojo.UserVipPO;
import lombok.Data;

import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/12/11 18:32
 * @description: TODO
 * @version: 1.0
 */
@Data
public class UserVipBO extends UserVipPO {
    private String phone;
    private Date createTime;
}
