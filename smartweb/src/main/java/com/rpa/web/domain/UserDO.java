package com.rpa.web.domain;

import com.rpa.web.pojo.UserPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/24 15:14
 * @description: 用户信息
 * @version: 1.0
 */
@Data
public class UserDO extends UserPO {
    /**
     * 应用版本名称
     */
    private String versionName;
    /**
     * android系统版本
     */
    private String buildRelease;
    /**
     * 手机厂商
     */
    private String manufacturer;
    /**
     * 手机型号
     */
    private String androidModel;
}
