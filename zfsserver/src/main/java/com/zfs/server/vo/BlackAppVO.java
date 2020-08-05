package com.zfs.server.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2020/1/14 18:45
 * @description: 应用黑名单
 * @version: 1.0
 */
@Data
public class BlackAppVO implements Serializable {

    private Long bid;
    private List<String> pkgs;
}
