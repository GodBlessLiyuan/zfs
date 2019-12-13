package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.DeviceMapper;
import com.rpa.common.pojo.DevicePO;
import com.rpa.web.common.PageHelper;
import com.rpa.web.vo.DeviceVO;
import com.rpa.web.service.IUnregisteredService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 11:46
 * @description: TODO
 * @version: 1.0
 */
@Service
public class UnregisteredServiceImpl implements IUnregisteredService {

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public DTPageInfo<DeviceVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<DevicePO> page = PageHelper.startPage(pageNum, pageSize);
        List<DevicePO> pos = deviceMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), DeviceVO.convert(pos));
    }
}
