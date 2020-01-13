package com.rpa.make.service.impl;

import com.rpa.make.service.IMakeService;
import org.springframework.stereotype.Service;

/**
 * @author: xiahui
 * @date: Created in 2020/1/11 17:06
 * @description: TODO
 * @version: 1.0
 */
@Service
public class MakeServiceImpl implements IMakeService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    @Resource
    private AvatarMapper avatarMapper;
    @Value("${file.uploadFolder}")
    private String rootDir;
    @Value("${file.projectDir}")
    private String projectDir;
    @Value("${file.avatarDir}")
    private String avatarDir;

    @Override
    public ResultVO make(AvatarMakeDTO dto) {
        AvatarPO po = avatarMapper.selectByPrimaryKey(dto.getAvaid());
        if (null == po) {
            return new ResultVO(2000);
        }

        AvatarMakeVO vo = new AvatarMakeVO();
        try {
            logger.info("avatarUrl：{}", rootDir + po.getUrl());
            vo.setUrl(FileUtil.rebuildApk(rootDir + po.getUrl(), rootDir,
                    FileUtil.genFilePath(projectDir + avatarDir, sdf.format(new Date())), dto));
        } catch (Exception e) {
            return new ResultVO(2000);
        }

        return new ResultVO<>(1000, vo);
    }
}
