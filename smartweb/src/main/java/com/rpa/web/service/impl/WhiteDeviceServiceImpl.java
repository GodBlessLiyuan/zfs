package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.web.domain.WhiteDeviceDO;
import com.rpa.web.dto.WhiteDeviceDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.exception.PromptException;
import com.rpa.web.mapper.DeviceImeiMapper;
import com.rpa.web.mapper.WhiteDeviceMapper;
import com.rpa.web.pojo.DeviceImeiPO;
import com.rpa.web.pojo.WhiteDevicePO;
import com.rpa.web.service.IWhiteDeviceService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 14:23
 * @description: 测试白名单
 * @version: 1.0
 */
@Service
public class WhiteDeviceServiceImpl implements IWhiteDeviceService {

    @Resource
    private WhiteDeviceMapper whiteDeviceMapper;
    @Resource
    private DeviceImeiMapper deviceImeiMapper;
    @Autowired
    private StringRedisTemplate template;

    @Override
    public DTPageInfo<WhiteDeviceDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<WhiteDeviceDO> page = PageHelper.offsetPage(pageNum, pageSize);
        List<WhiteDeviceDO> pos = whiteDeviceMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), WhiteDeviceDTO.convert(pos));
    }

    @Override
    public ResultVO insert(String imei, String extra, int aId) {
        List<DeviceImeiPO> deviceImeiPOs = deviceImeiMapper.queryByImei(imei);
        if (deviceImeiPOs == null || deviceImeiPOs.size() == 0) {
            // 未找到对应的deviceId
            throw new PromptException(ExceptionEnum.IMEI_INPUT_ERROR);
        }

        // deviced 与 imei 是1-n关系,故这里deviceId有且仅有一个
        long deviceId = deviceImeiPOs.get(0).getDeviceId();
        List<WhiteDevicePO> whiteDevicePOs = whiteDeviceMapper.queryByDeviceId(deviceId);
        if (whiteDevicePOs != null && whiteDevicePOs.size() > 0) {
            // 已存在在白名单里
            throw new PromptException(ExceptionEnum.IMEI_EXIST);
        }

        WhiteDevicePO po = new WhiteDevicePO();

        po.setDeviceId(deviceId);
        po.setExtra(extra);
        po.setCreateTime(new Date());
        po.setaId(aId);

        whiteDeviceMapper.insert(po);

        this.deleteRedis();

        return ResultVOUtil.success();
    }

    @Override
    public int deleteByDeviceId(int deviceId) {
        int first = whiteDeviceMapper.deleteByDeviceId(deviceId);
        this.deleteRedis();
        return first;
    }


    /**
     * 删除对应的Redis
     */
    private void deleteRedis() {
        Set<String> redisKeys = template.keys(RedisKeyUtil.genWhiteDeviceRedisKey("*"));
        if (!CollectionUtils.isEmpty(redisKeys)) {
            template.delete(redisKeys);
        }
    }
}
