package com.rpa.server.service.impl;

import com.rpa.common.mapper.BlackAppMapper;
import com.rpa.common.pojo.BlackAppPO;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.BlackAppDTO;
import com.rpa.server.service.IBlackAppService;
import com.rpa.server.vo.BlackAppVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2020/1/14 18:35
 * @description: 应用黑名单
 * @version: 1.0
 */
@Service
public class BlackServiceImpl implements IBlackAppService {
    @Resource
    private BlackAppMapper blackAppMapper;

    @Override
    public ResultVO getBlankPkgs(BlackAppDTO dto) {
        List<BlackAppPO> pos = blackAppMapper.queryById(dto.getBid());
        if (null == pos || pos.size() == 0) {
            return new ResultVO(1025);
        }

        BlackAppVO vo = new BlackAppVO();
        vo.setBid(pos.get(0).getBlankId());
        List<String> pkgs = new ArrayList<>();
        for (BlackAppPO po : pos) {
            pkgs.add(po.getPackageName());
        }
        vo.setPkgs(pkgs);

        return new ResultVO<>(1000, vo);
    }
}
