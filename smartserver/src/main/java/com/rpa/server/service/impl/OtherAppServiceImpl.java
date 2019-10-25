package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.OtherAppDTO;
import com.rpa.server.mapper.OtherAppMapper;
import com.rpa.server.pojo.OtherAppPO;
import com.rpa.server.service.IOtherAppService;
import com.rpa.server.vo.OtherAppVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 11:15
 * @description: 其他应用
 * @version: 1.0
 */
@Service
public class OtherAppServiceImpl implements IOtherAppService {
    @Resource
    private OtherAppMapper otherAppMapper;

    @Override
    public ResultVO query(OtherAppDTO dto) {
        List<OtherAppPO> pos = otherAppMapper.queryAll();

        List<OtherAppVO> vos = new ArrayList<>();
        for (OtherAppPO po : pos) {
            OtherAppVO vo = new OtherAppVO();
            vo.setName(po.getoName());
            vo.setIconurl(po.getIconUrl());
            vo.setType(po.getDownloadType());
            vo.setAppurl(po.getAppUrl());
            vo.setExtra(po.getExtra());
            vos.add(vo);
        }

        return new ResultVO<>(1000, vos);
    }
}
