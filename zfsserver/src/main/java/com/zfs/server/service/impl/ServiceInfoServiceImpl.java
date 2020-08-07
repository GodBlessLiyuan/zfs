package com.zfs.server.service.impl;

import com.zfs.common.mapper.ServiceNumberMapper;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.service.IServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-07 16:24
 */
@Service
public class ServiceInfoServiceImpl implements IServiceInfoService {
    @Autowired
    private ServiceNumberMapper serviceNumberMapper;
    @Override
    public ResultVO query() {
        List<String> numbers=serviceNumberMapper.queryNumbers();
        return new ResultVO(1000,numbers);
    }
}
