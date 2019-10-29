package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.FunctionvideoDTO;
import com.rpa.server.mapper.FunctionVideoMapper;
import com.rpa.server.service.FunctionvideoService;
import com.rpa.server.vo.FunctionvideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: dangyi
 * @date: Created in 14:39 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Service
public class FunctionvideoServiceImpl implements FunctionvideoService {

    @Autowired
    private FunctionVideoMapper functionVideoMapper;

    @Override
    public ResultVO query(FunctionvideoDTO dto) {

        String url = this.functionVideoMapper.queryUrl(dto.getFunction());
        FunctionvideoVO vo = new FunctionvideoVO();
        vo.setUrl(url);

        return new ResultVO(1000, vo);
    }
}
