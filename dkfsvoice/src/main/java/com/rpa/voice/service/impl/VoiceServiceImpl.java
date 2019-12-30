package com.rpa.voice.service.impl;

import com.rpa.common.constant.ModuleConstant;
import com.rpa.common.mapper.VoiceShareMapper;
import com.rpa.common.pojo.VoiceSharePO;
import com.rpa.common.utils.FileUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.voice.dto.VoiceShareDTO;
import com.rpa.voice.dto.VoiceUploadDTO;
import com.rpa.voice.service.IVoiceService;
import com.rpa.voice.vo.VoiceShareCodeVO;
import com.rpa.voice.vo.VoiceShareVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author: xiahui
 * @date: Created in 2019/12/3 19:26
 * @description: 语音盒子服务
 * @version: 1.0
 */
@Service
public class VoiceServiceImpl implements IVoiceService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Resource
    private VoiceShareMapper voiceShareMapper;
    @Value("${project.baseUrl}")
    private String baseUrl;
    @Value("${project.shareUrl}")
    private String shareUrl;
    @Value("${file.rootDir}")
    private String rootDir;
    @Value("${file.proDir}")
    private String proDir;
    @Value("${file.voiceDir}")
    private String voiceDir;
    @Value("${wxpayconfig.appid}")
    private String appId;

    @Override
    public ResultVO share(VoiceShareDTO dto) {
        VoiceSharePO po = new VoiceSharePO();
        po.setDeviceId(dto.getId());
        po.setUserId(dto.getUd());
        po.setUserDeviceId(dto.getUdd());
        po.setTotal(dto.getTotal());
        po.setTitle(dto.getTitle());
        po.setExtra(dto.getExtra());
        Date curDate = new Date();
        po.setCreateTime(curDate);
        po.setStatus((byte) 1);
        String random = UUID.randomUUID().toString().replace("-", "");
        po.setPath(FileUtil.genFilePath(proDir, voiceDir, sdf.format(curDate),
                ModuleConstant.VOICE + "_" + curDate.getTime() + random));
        po.setUrl(random);
        voiceShareMapper.insert(po);

        VoiceShareVO vo = new VoiceShareVO();
        vo.setUrl(shareUrl + po.getUrl());
        vo.setVid(po.getVoiceId());
        vo.setAppid(appId);

        return new ResultVO<>(1000, vo);
    }

    @Override
    public ResultVO upload(VoiceUploadDTO dto) {
        VoiceSharePO voiceSharePO = voiceShareMapper.selectByPrimaryKey(dto.getVid());
        if (null == voiceSharePO) {
            return new ResultVO<>(2000);
        }

        String fileName = FileUtil.genFileName(ModuleConstant.VOICE, dto.getSuffix(), dto.getVid(), dto.getNum());
        String uploadPath = FileUtil.uploadBase64(rootDir, voiceSharePO.getPath(), fileName, dto.getVoice());

        if (voiceSharePO.getTotal().equals(dto.getNum())) {
            voiceSharePO.setStatus((byte) 2);
            voiceShareMapper.updateByPrimaryKey(voiceSharePO);
        }

        return new ResultVO<>(1000, baseUrl + uploadPath);
    }

    @Override
    public ResultVO shareCode(String shareCode) {
        VoiceSharePO voiceSharePO = voiceShareMapper.queryByCode(shareCode);
        if (null == voiceSharePO) {
            return new ResultVO(2000);
        }

        List<String> voiceUrls = new LinkedList<>();
        String path = voiceSharePO.getPath();
        for (int i = 1; i <= voiceSharePO.getTotal(); i++) {
            voiceUrls.add(baseUrl + path + FileUtil.genFileName(ModuleConstant.VOICE, "mp3", voiceSharePO.getVoiceId(),
                    i));
        }

        VoiceShareCodeVO vo = new VoiceShareCodeVO();
        vo.setVoices(voiceUrls);

        return new ResultVO<>(1000, vo);
    }
}
