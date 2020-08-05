package com.zfs.server.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: dangyi
 * @date: Created in 10:05 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Data
public class WxsupportVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer index;
    private List<String> pkgs;

}
