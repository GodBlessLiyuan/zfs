package com.rpa.voice.controller;

import com.rpa.voice.service.IVoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: xiahui
 * @date: Created in 2019/12/4 18:38
 * @description: 语音分享
 * @version: 1.0
 */
@RequestMapping("v1.1")
@Controller
public class PageController {
    @Autowired
    private IVoiceService service;

    @GetMapping(value = "share/{shareCode}")
    public String shareCode(@PathVariable String shareCode, ModelMap map) {
        ResultVO vo = service.shareCode(shareCode);
        map.put("res", vo);
        return "voicebox_index";
    }
}
