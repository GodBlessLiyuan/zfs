package com.rpa.server.service.impl;

import com.rpa.common.mapper.BlankAppMapper;
import com.rpa.common.pojo.BlankAppPO;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.BlankAppDTO;
import com.rpa.server.service.IBlankAppService;
import com.rpa.server.vo.BlankAppVO;
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
public class BlankServiceImpl implements IBlankAppService {
    @Resource
    private BlankAppMapper blankAppMapper;

    @Override
    public ResultVO getBlankPkgs(BlankAppDTO dto) {
        List<BlankAppPO> pos = blankAppMapper.queryById(dto.getBid());
        if (null == pos || pos.size() == 0) {
            return new ResultVO(1025);
        }

        BlankAppVO vo = new BlankAppVO();
        vo.setBid(pos.get(0).getBlankId());
        List<String> pkgs = new ArrayList<>();
        for (BlankAppPO po : pos) {
            pkgs.add(po.getPackageName());
        }
        vo.setPkgs(pkgs);

        return new ResultVO<>(1000, vo);
    }
}
