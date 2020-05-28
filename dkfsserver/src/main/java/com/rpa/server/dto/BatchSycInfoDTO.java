package com.rpa.server.dto;

import com.rpa.common.pojo.SoftChannelPO;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 批次同步信息，用于发送到智能助手
 * @author: liyuan
 * @data 2020-05-20 19:43
 */
@Data
public class BatchSycInfoDTO implements Serializable {
    private Integer day;
    private String phone;
    private UserDouDTO userDouDTO;
    private String key;
}
