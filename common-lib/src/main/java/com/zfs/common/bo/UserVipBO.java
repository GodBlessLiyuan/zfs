package com.zfs.common.bo;

import com.zfs.common.pojo.UserVipPO;
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
