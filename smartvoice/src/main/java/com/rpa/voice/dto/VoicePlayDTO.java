package com.rpa.voice.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/12/3 20:19
 * @description: TODO
 * @version: 1.0
 */
@Data
public class VoicePlayDTO implements Serializable {
    private static final long SerialVersionUID = 1L;

    private Long vid;
    private Integer num;
}
