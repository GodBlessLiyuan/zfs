package com.zfs.common.bo;

import com.zfs.common.pojo.UserPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/11 16:02
 * @description: TODO
 * @version: 1.0
 */
@Data
public class UserBO extends UserPO {
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
