package com.rpa.make.service.impl;

import com.rpa.common.dto.MakeDTO;
import com.rpa.common.mapper.AvatarMapper;
import com.rpa.common.pojo.AvatarPO;
import com.rpa.common.utils.FileUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.make.service.IMakeService;
import com.rpa.make.vo.MakeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2020/1/11 17:06
 * @description: TODO
 * @version: 1.0
 */
@Service
public class MakeServiceImpl implements IMakeService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = LoggerFactory.getLogger(MakeServiceImpl.class);

    @Resource
    private AvatarMapper avatarMapper;
    @Value("${file.uploadFolder}")
    private String rootDir;
    @Value("${file.projectDir}")
    private String projectDir;
    @Value("${file.avatarDir}")
    private String avatarDir;
    @Value("${file.publicPath}")
    private String publicPath;


    @Override
    public ResultVO make(MakeDTO dto) {
        AvatarPO po = avatarMapper.selectByPrimaryKey(dto.getAvaid());
        if (null == po) {
            return new ResultVO(2000);
        }

        MakeVO vo = new MakeVO();
        try {
            logger.info("avatarUrl：{}", rootDir + po.getUrl());
            vo.setUrl(publicPath + FileUtil.rebuildApk(rootDir + po.getUrl(), rootDir,
                    projectDir + avatarDir + sdf.format(new Date()) + "/", dto));
        } catch (Exception e) {
            return new ResultVO(2000);
        }

        return new ResultVO<>(1000, vo);
    }
}
