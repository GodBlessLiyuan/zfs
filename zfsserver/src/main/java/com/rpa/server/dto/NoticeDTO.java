package com.rpa.server.dto;

import com.rpa.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:44
 * @description: 通知消息
 * @version: 1.0
 */
@Data
public class NoticeDTO extends VerifyDTO {
    /**
     * 用户唯一标识id
     */
    private Long ud;
    /**
     * 用户唯一标识id的md5
     */
    private String md;
    /**
     * 用户设备唯一标识
     */
    private Integer udd;
}
