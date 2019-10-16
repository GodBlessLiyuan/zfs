package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 18:52
 * @description: 用户会员
 * @version: 1.0
 */
@Data
public class UserVipVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户vip过期时间
     */
    private Date vip;
    /**
     * 年费会员vip过期时间
     */
    private Date advanced;

}
