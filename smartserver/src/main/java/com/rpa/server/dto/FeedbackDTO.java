package com.rpa.server.dto;

import com.rpa.server.dto.base.VerifyDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 10:59
 * @description: 用户反馈
 * @version: 1.0
 */
@Data
public class FeedbackDTO extends VerifyDTO {

    private Long ud;
    private String um;
    private Integer udd;
    private String context;
    private String picdata;
    private String ph;
    private String factory;
    private String model;
    private Integer softv;
    private Byte osv;
    private String osre;
}
