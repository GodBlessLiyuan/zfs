package com.rpa.voice.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.voice.dto.VoicePlayDTO;
import com.rpa.voice.dto.VoiceShareDTO;
import com.rpa.voice.dto.VoiceUploadDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/12/3 19:26
 * @description: 语音盒子接口
 * @version: 1.0
 */
public interface IVoiceService {

    /**
     * 语音分享
     *
     * @param dto
     * @return
     */
    ResultVO share(VoiceShareDTO dto);

    /**
     * 语音上传
     *
     * @param dto
     * @return
     */
    ResultVO upload(VoiceUploadDTO dto);

    /**
     * 语音播放
     *
     * @param dto
     * @return
     */
    ResultVO play(VoicePlayDTO dto);
}
