package com.rpa.voice.dto;

import com.rpa.common.dto.TokenDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/3 19:32
 * @description: TODO
 * @version: 1.0
 */
@Data
public class VoiceShareDTO extends TokenDTO {
    private String verify;
    private String um;
    private String extra;
    private Integer total;
}
