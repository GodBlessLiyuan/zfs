package com.rpa.voice.controller;

import com.rpa.common.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/12/2 11:38
 * @description: 语音盒子
 * @version: 1.0
 */
@RestController
public class VoiceController {

    @PostMapping("voiceshare")
    public ResultVO voiceShare() {
        return null;
    }
}
