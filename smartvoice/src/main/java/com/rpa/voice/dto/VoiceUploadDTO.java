package com.rpa.voice.dto;

import com.rpa.common.dto.TokenDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/3 19:34
 * @description: TODO
 * @version: 1.0
 */
@Data
public class VoiceUploadDTO extends TokenDTO {
    private Integer num;
    private Long vid;
    private String voice;
    private String suffix;
}
