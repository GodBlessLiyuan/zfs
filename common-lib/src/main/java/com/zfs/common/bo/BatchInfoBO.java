package com.zfs.common.bo;

import com.zfs.common.pojo.BatchInfoPO;
import lombok.Data;

import java.util.Date;

/**
 * @author: dangyi
 * @date: Created in 10:31 2019/10/9
 * @version: 1.0.0
 * @description:
 */
@Data
public class BatchInfoBO extends BatchInfoPO {

    private String chanNickname;

    private String chanName;

    private String comTypeName;

    private Date createTime;

    private String phone;

    /**
     * 用户渠道
     */
    private String userChanName;
}
