package com.rpa.voice.controller;

import com.rpa.common.utils.VerifyUtil;
import com.rpa.voice.dto.VoiceShareDTO;
import com.rpa.voice.dto.VoiceUploadDTO;
import com.rpa.voice.service.IVoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/12/2 11:38
 * @description: 语音盒子
 * @version: 1.0
 */
@RequestMapping("v1.1")
@RestController
public class VoiceController {
    @Autowired
    private IVoiceService service;

    @PostMapping("share")
    public ResultVO share(@RequestBody VoiceShareDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }

        return service.share(dto);
    }

    @PostMapping("upload")
    public ResultVO upload(@RequestBody VoiceUploadDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }

        return service.upload(dto);
    }
}
