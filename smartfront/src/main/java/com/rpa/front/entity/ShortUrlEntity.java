package com.rpa.front.entity;

import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/11/8 11:06
 * @description: 短链接返回数据
 * @version: 1.0
 */
@Data
public class ShortUrlEntity {
    private int code;
    private String errMsg;
    private String longUrl;
    private String shortUrl;
}
