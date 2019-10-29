package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.WxsupportDTO;
import com.rpa.server.mapper.WxSupportMapper;
import com.rpa.server.service.WxsupportService;
import com.rpa.server.vo.WxsupportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: dangyi
 * @date: Created in 9:51 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Service
public class WxsupportServiceImpl implements WxsupportService {

    @Autowired
    private WxSupportMapper wxSupportMapper;

    @Override
    public ResultVO query(WxsupportDTO dto) {

        WxsupportVO vo = new WxsupportVO();

        // 从数据库查询出最新的ID
        int maxId = this.wxSupportMapper.queryMaxId();
        vo.setIndex(maxId);

        if (dto.getIndex() != maxId) {
            List<String> pkgs = this.wxSupportMapper.queryPkgs();
            vo.setPkgs(pkgs);
        }

        return new ResultVO<>(1000, vo);
    }
}
