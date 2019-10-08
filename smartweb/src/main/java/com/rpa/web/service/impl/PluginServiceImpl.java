package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.PluginDTO;
import com.rpa.web.mapper.AppPluChMapper;
import com.rpa.web.mapper.PluginMapper;
import com.rpa.web.pojo.AppPO;
import com.rpa.web.pojo.AppPluChPO;
import com.rpa.web.pojo.PluginPO;
import com.rpa.web.service.IPluginService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 20:09
 * @description: 插件更新
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class PluginServiceImpl implements IPluginService {

    @Resource
    private PluginMapper pluginMapper;

    @Resource
    private AppPluChMapper appPluChMapper;


    @Override
    public DTPageInfo<PluginDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<PluginPO> page = PageHelper.startPage(pageNum, pageSize);
        List<PluginPO> pos = pluginMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), PluginDTO.convert(pos));
    }

    @Transactional(rollbackFor = {})
    @Override
    public int insert(String url, int appId, int[] softChannel, String context, String extra) {
        PluginPO pluginPO = new PluginPO();
        // 根据URL获取插件大小、插件md5值
        pluginPO.setSize(10000);
        pluginPO.setMd5("ASWXKAJDLKFJALKJDFKJASDFASDF");

        pluginPO.setStatus((byte) 1);
        pluginPO.setContext(context);
        pluginPO.setExtra(extra);
        pluginPO.setaId(1);
        pluginPO.setCreateTime(new Date());
        pluginPO.setDr((byte) 1);

        int frist = pluginMapper.insert(pluginPO);

        List<AppPluChPO> appPluChPOs = new ArrayList<>();
        for (int chanId : softChannel) {
            AppPluChPO appPluChPO = new AppPluChPO();

            appPluChPO.setAppId(appId);
            appPluChPO.setSoftChannelId(chanId);
            appPluChPO.setPluginId(pluginPO.getPluginId());
            appPluChPO.setCreateTime(new Date());
            appPluChPO.setStatus((byte) 1);

            appPluChPOs.add(appPluChPO);
        }

        int secend = appPluChMapper.batchInsert(appPluChPOs);

        return frist + secend;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int updateStatus(int pluginId, byte status) {
        PluginPO pluginPO = pluginMapper.selectByPrimaryKey(pluginId);
        pluginPO.setPublishTime(status == 2 ? new Date() : null);
        pluginPO.setStatus(status);
        int frist = pluginMapper.updateByPrimaryKey(pluginPO);

        int secend = appPluChMapper.updateStatus(pluginId, status);

        return frist + secend;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(int pluginId) {
        int frist = pluginMapper.deleteByPrimaryKey(pluginId);
        int secend = appPluChMapper.deleteByAppId(pluginId);
        return frist + secend;
    }
}
