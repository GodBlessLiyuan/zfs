package com.rpa.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-05-20 19:43
 */
@Data
public class BatchSycInfoDTO implements Serializable {
    private String key;
    /**
     * 用户唯一标识id
     */
    private Long ud;
}
