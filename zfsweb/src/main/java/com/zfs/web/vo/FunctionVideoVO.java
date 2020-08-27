package com.zfs.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * t_functionvideo
 * @author 
 */
@Data
public class FunctionVideoVO implements Serializable {
    private Integer functionId;

    private String funName;

    private String url;

    private String extra;

    private Integer aId;

    private Date createTime;

    private Date updateTime;

    private String operator;
    private List<String> urls;
    private String origName;

}