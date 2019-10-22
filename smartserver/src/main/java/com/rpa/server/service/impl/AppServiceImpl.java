package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.AppDTO;
import com.rpa.server.mapper.AppMapper;
import com.rpa.server.pojo.AppPO;
import com.rpa.server.service.IAppService;
import com.rpa.server.vo.AppVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 8:59
 * @description: 版本更新
 * @version: 1.0
 */
@Service
public class AppServiceImpl implements IAppService {
    @Resource
    private AppMapper appMapper;

    @Override
    public ResultVO check(AppDTO dto) {
        AppPO appPO = appMapper.queryMaxByVerId(dto.getSoftv());
        if (appPO == null) {
            // 最新版本
            return new ResultVO(1008);
        }

        // 需要更新
        AppVO vo = new AppVO();
        vo.setUrl(appPO.getUrl());
        vo.setMd5(appPO.getMd5());
        vo.setType(appPO.getUpdateType());
        return new ResultVO<>(1009, vo);
    }
}
