package com.zfs.web.vo;

import com.zfs.web.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_notice
 * @author 
 */
@Data
public class NoticeVO implements Serializable {
    private Integer noticeId;

    private String title;

    private String text;

    /**
     * 1文本 2 图片 3 文本加图片
     */
    private Byte type;

    private String showTime;
    private String endShowTime;
    private String noticeShowTime;
    private String startTime;

    /**
     * 1 关闭  2 开始 3 删除
     */
    private Integer status;

    private String endTime;

    private Date createTime;

    private String url;

    private String picurl;

    private String operator;
    private String[] menbers;
    private String noticeMenbers;
}