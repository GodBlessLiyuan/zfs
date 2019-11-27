package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.ShareactivityDTO;
import com.rpa.server.mapper.ShareActivityMapper;
import com.rpa.server.pojo.ShareActivityPO;
import com.rpa.server.service.ShareactivityService;
import com.rpa.server.vo.ShareactivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: dangyi
 * @date: Created in 11:20 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Service
public class ShareacticityServiceImpl implements ShareactivityService {

    @Autowired
    private ShareActivityMapper shareActivityMapper;

    @Value("${file.publicPath}")
    private String publicPath;

    @Override
    public ResultVO query(ShareactivityDTO dto) {

        List<ShareActivityPO> pos = this.shareActivityMapper.query();
        List<ShareactivityVO> vos = new ArrayList<>();
        // 将查询到的 PO 转换为 VO
        for (ShareActivityPO po : pos) {
            ShareactivityVO vo = new ShareactivityVO();
            vo.setMid(po.getMaterialId());
            vo.setType(po.getType().intValue());
            if (po.getType() == 1) {
                vo.setContent(po.getContent());
            } else {
                vo.setContent(publicPath + po.getContent());
            }

            vos.add(vo);
        }

        return new ResultVO(1000,vos);
    }
}
