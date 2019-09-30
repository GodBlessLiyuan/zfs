package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.WhilteDeviceDTO;
import com.rpa.web.mapper.WhilteDeviceMapper;
import com.rpa.web.pojo.WhilteDevicePO;
import com.rpa.web.service.IWhilteDeviceService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 14:23
 * @description: 测试白名单
 * @version: 1.0
 */
@Service
public class WhiteDeviceServiceImpl implements IWhilteDeviceService {

    @Resource
    private WhilteDeviceMapper whilteDeviceMapper;

    @Override
    public DTPageInfo<WhilteDeviceDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<WhilteDevicePO> page = PageHelper.offsetPage(pageNum, pageSize);
        List<WhilteDevicePO> pos = whilteDeviceMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), WhilteDeviceDTO.convert(pos));
    }

    @Override
    public int insert(String imei, String extra) {
        return 0;
    }

    @Override
    public int deleteByDeviceId(int deviceId) {
        return whilteDeviceMapper.deleteByDeviceId(deviceId);
    }
}
