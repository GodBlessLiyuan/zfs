package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:54
 * @description: 通知消息
 * @version: 1.0
 */
@Data
public class NoticeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 通知id
     */
    private Integer noticeid;
    /**
     * 广告类型  1 文字  2 图片
     */
    private Byte type;
    /**
     * 标题
     */
    private String title;
    /**
     * 文字内容 或 图片的路径
     */
    private String text;
    /**
     * 通知每天展示时间
     */
    private Date showtime;
    /**
     * 跳转链接
     */
    private String url;

}
