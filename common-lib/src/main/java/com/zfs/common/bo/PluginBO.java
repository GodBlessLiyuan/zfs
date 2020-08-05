package com.zfs.common.bo;

import com.zfs.common.pojo.PluginPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/11 18:44
 * @description: TODO
 * @version: 1.0
 */
@Data
public class PluginBO extends PluginPO {
    private int softChannelId;
    private int appId;
    private String username;
    private String name;
    private String versionName;
}
