package com.zfs.server.service.impl;

import com.zfs.common.mapper.BlackAppMapper;
import com.zfs.common.pojo.BlackAppPO;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.BlackAppDTO;
import com.zfs.server.service.IBlackAppService;
import com.zfs.server.vo.BlackAppVO;
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
        vo.setBid(pos.get(0).getBlackId());
        List<String> pkgs = new ArrayList<>();
        for (BlackAppPO po : pos) {
            pkgs.add(po.getPackageName());
        }
        vo.setPkgs(pkgs);

        return new ResultVO<>(1000, vo);
    }
}
