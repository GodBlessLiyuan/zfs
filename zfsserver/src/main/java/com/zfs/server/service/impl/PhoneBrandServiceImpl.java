package com.zfs.server.service.impl;

import com.zfs.common.mapper.PhoneBrandMapper;
import com.zfs.common.pojo.PhoneBrandPO;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.service.IPhoneBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-17 16:06
 */
@Service
public class PhoneBrandServiceImpl implements IPhoneBrandService {
    @Autowired
    private PhoneBrandMapper phoneBrandMapper;
    @Override
    public ResultVO phoneBrand() {
        List<PhoneBrandPO> list = phoneBrandMapper.queryAll();
        return new ResultVO(1000,list);
    }
}
