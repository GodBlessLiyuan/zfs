package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.mapper.DeviceMapper;
import com.zfs.common.pojo.DevicePO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.vo.DeviceVO;
import com.zfs.web.service.IUnregisteredService;
import com.zfs.web.utils.DTPageInfo;
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
