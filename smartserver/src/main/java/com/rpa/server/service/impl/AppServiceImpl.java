package com.rpa.server.service.impl;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.AppDTO;
import com.rpa.server.mapper.AppMapper;
import com.rpa.server.pojo.AppPO;
import com.rpa.server.service.IAppService;
import com.rpa.server.utils.RedisCacheUtil;
import com.rpa.server.utils.RequestUtil;
import com.rpa.server.vo.AppVO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 8:59
 * @description: 版本更新
 * @version: 1.0
 */
@Service
public class AppServiceImpl implements IAppService {
    @Resource
    private AppMapper appMapper;
    @Resource
    private RedisCacheUtil cache;
    @Value("${file.publicPath}")
    private String filePublicPath;
    @Autowired
    private AmqpTemplate template;

    @Override
    public ResultVO check(AppDTO dto, HttpServletRequest req) {
        // 从Redis中取出设备白名单
        int status = cache.checkWhiteDeviceByDevId(dto.getId()) ? 0 : 2;
        int channId = cache.getSoftChannelId(dto.getChannel());
        AppPO appPO = appMapper.queryMaxByVerId(dto.getSoftv(), status, channId);
        if (appPO == null) {
            // 最新版本
            return new ResultVO(1008);
        }

        // 需要更新
        AppVO vo = new AppVO();
        vo.setUrl(filePublicPath + appPO.getUrl());
        vo.setMd5(appPO.getMd5());
        vo.setType(appPO.getUpdateType());
        vo.setContext(appPO.getContext());
        vo.setCode(appPO.getVersioncode());
        vo.setVersionname(appPO.getVersionname());

        mqForDeviceInfo(dto, req);

        return new ResultVO<>(1009, vo);
    }

    /**
     * @author: dangyi
     * @date: Created in 2019/11/14 15:35
     * @description: 将用户设备访问信息转发给RabbitMQ
     */
    private void mqForDeviceInfo(AppDTO dto, HttpServletRequest req) {
        // 创建map集合，封装用户设备访问信息
        Map<String, Object> deviceInfo = new HashMap<>();
        deviceInfo.put("deviceId", dto.getId());
        deviceInfo.put("visitTime", new Date());
        deviceInfo.put("ip", RequestUtil.getIpAddr(req));
        this.template.convertAndSend("device_statistics", deviceInfo);
    }
}
