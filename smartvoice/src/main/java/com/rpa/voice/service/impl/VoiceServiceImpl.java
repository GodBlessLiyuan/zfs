package com.rpa.voice.service.impl;

import com.rpa.common.vo.ResultVO;
import com.rpa.voice.dto.VoicePlayDTO;
import com.rpa.voice.dto.VoiceShareDTO;
import com.rpa.voice.dto.VoiceUploadDTO;
import com.rpa.voice.service.IVoiceService;
import org.springframework.stereotype.Service;

/**
 * @author: xiahui
 * @date: Created in 2019/12/3 19:26
 * @description: 语音盒子服务
 * @version: 1.0
 */
@Service
public class VoiceServiceImpl implements IVoiceService {

    @Override
    public ResultVO share(VoiceShareDTO dto) {
        return null;
    }

    @Override
    public ResultVO upload(VoiceUploadDTO dto) {
        return null;
    }

    @Override
    public ResultVO play(VoicePlayDTO dto) {
        return null;
    }
}
