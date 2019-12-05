package com.rpa.voice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/12/3 19:39
 * @description: TODO
 * @version: 1.0
 */
@Data
public class VoiceShareVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long vid;
    private String url;
    private String appid;
}
