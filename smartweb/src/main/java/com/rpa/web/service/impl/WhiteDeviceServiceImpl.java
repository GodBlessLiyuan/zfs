package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.WhilteDeviceDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.exception.PromptException;
import com.rpa.web.mapper.DeviceImeiMapper;
import com.rpa.web.mapper.WhilteDeviceMapper;
import com.rpa.web.pojo.DeviceImeiPO;
import com.rpa.web.pojo.WhilteDevicePO;
import com.rpa.web.service.IWhilteDeviceService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private DeviceImeiMapper deviceImeiMapper;

    @Override
    public DTPageInfo<WhilteDeviceDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<WhilteDevicePO> page = PageHelper.offsetPage(pageNum, pageSize);
        List<WhilteDevicePO> pos = whilteDeviceMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), WhilteDeviceDTO.convert(pos));
    }

    @Override
    public ResultVO insert(String imei, String extra) {
        List<DeviceImeiPO> deviceImeiPOs = deviceImeiMapper.queryByImei(imei);
        if (deviceImeiPOs == null || deviceImeiPOs.size() == 0) {
            // 未找到对应的deviceId
            throw new PromptException(ExceptionEnum.IMEI_INPUT_ERROR);
        }

        // deviced 与 imei 是1-n关系,故这里deviceId有且仅有一个
        long deviceId = deviceImeiPOs.get(0).getDeviceId();
        List<WhilteDevicePO> whilteDevicePOs = whilteDeviceMapper.queryByDeviceId(deviceId);
        if (whilteDevicePOs != null && whilteDevicePOs.size() > 0) {
            // 已存在在白名单里
            throw new PromptException(ExceptionEnum.IMEI_EXIST);
        }

        WhilteDevicePO po = new WhilteDevicePO();

        po.setDeviceId(deviceId);
        po.setExtra(extra);
        po.setCreateTime(new Date());
        po.setaId(1);

        whilteDeviceMapper.insert(po);

        return ResultVOUtil.success();
    }

    @Override
    public int deleteByDeviceId(int deviceId) {
        return whilteDeviceMapper.deleteByDeviceId(deviceId);
    }
}
