package com.rpa.common.bo;

import com.rpa.common.pojo.AvatarPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/12 20:30
 * @description: TODO
 * @version: 1.0
 */
@Data
public class AvatarBO extends AvatarPO {
    private String username;
    private String versionName;
    private String appVersionName;
    private int chanId;
    private String chanName;
    private int appId;
}
