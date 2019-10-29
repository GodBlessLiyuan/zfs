package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.BannerConfigDTO;
import com.rpa.server.mapper.BannerConfigMapper;
import com.rpa.server.pojo.BannerConfigPO;
import com.rpa.server.service.IBannerConfigService;
import com.rpa.server.vo.BannerConfigVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:10
 * @description: banner广告信息
 * @version: 1.0
 */
@Service
public class BannerConfigServiceImpl implements IBannerConfigService {
    @Resource
    private BannerConfigMapper bannerConfigMapper;
    @Value("${file.publicPath}")
    private String publicPath;

    @Override
    public ResultVO queryBanConf(BannerConfigDTO dto) {
        List<BannerConfigPO> bannerConfigPOs = bannerConfigMapper.queryAll();
        if (bannerConfigPOs == null || bannerConfigPOs.size() == 0) {
            return new ResultVO(1000);
        }

        List<BannerConfigVO> vos = new ArrayList<>();
        for (BannerConfigPO po : bannerConfigPOs) {
            BannerConfigVO vo = new BannerConfigVO();
            vo.setAdid(po.getBannerId());
            if (po.getUrl() != null && !"".equals(po.getUrl())) {
                vo.setLink(publicPath + po.getUrl());
            }
            if (po.getPicPath() != null && !"".equals(po.getPicPath())) {
                vo.setPicpath(publicPath + po.getPicPath());
            }
            vos.add(vo);
        }
        return new ResultVO<>(1000, vos);
    }
}
