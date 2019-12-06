package com.rpa.voice.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/12/6 10:19
 * @description: 语音分享码返回结果
 * @version: 1.0
 */
@Data
public class VoiceShareCodeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> voices;
}
