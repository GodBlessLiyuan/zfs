package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:16
 * @description: banner广告信息
 * @version: 1.0
 */
@Data
public class BannerConfigVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * adid: 广告id
     */
    private Integer adid;
    /**
     * link: 跳转链接
     */
    private String link;
    /**
     * picpath: 图片的路径
     */
    private String picpath;
}
