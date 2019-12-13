package com.rpa.web.service.impl;

import com.rpa.common.bo.PluginBO;
import com.rpa.common.mapper.AppPluChMapper;
import com.rpa.common.mapper.PluginMapper;
import com.rpa.common.pojo.AppPluChPO;
import com.rpa.common.pojo.PluginPO;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.web.vo.PluginVO;
import com.rpa.web.service.IPluginService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.FileUtil;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

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
    @Autowired
    private StringRedisTemplate template;

    @Value("${file.pluginDir}")
    private String pluginDir;

    @Override
    public DTPageInfo<PluginVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        PageHelper.startPage(pageNum, pageSize);
        List<PluginBO> pos = pluginMapper.query(reqData);
        List<PluginVO> dtos = PluginVO.convert(pos);
        return new DTPageInfo<>(draw, dtos.size(), dtos);
    }

    @Override
    public List<PluginVO> queryById(int pluginId) {
        return PluginVO.convert(pluginMapper.queryById(pluginId));
    }

    @Override
    public List<Integer> querySoftChannelByIds(int pluginId, int appId) {
        List<AppPluChPO> appPluChPOs = appPluChMapper.queryByIds(pluginId, appId);

        List<Integer> softChannelIds = new ArrayList<>();
        for (AppPluChPO po : appPluChPOs) {
            softChannelIds.add(po.getSoftChannelId());
        }

        return softChannelIds;
    }

    @Transactional(rollbackFor = {})
    @Override
    public ResultVO insert(MultipartFile file, int appId, int[] softChannel, String context, String extra, int aId) {
        PluginPO pluginPO = new PluginPO();
        this.setPluginPObyFile(file, pluginPO);

        pluginPO.setStatus((byte) 1);
        pluginPO.setContext(context);
        pluginPO.setExtra(extra);
        pluginPO.setaId(aId);
        pluginPO.setCreateTime(new Date());
        pluginPO.setDr((byte) 1);

        pluginMapper.insert(pluginPO);

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

        appPluChMapper.batchInsert(appPluChPOs);

        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int update(int pluginId, MultipartFile file, int appId, int[] softChannel, String context, String extra) {
        PluginPO pluginPO = pluginMapper.selectByPrimaryKey(pluginId);
        if (file != null && !"".equals(file)) {
            this.setPluginPObyFile(file, pluginPO);
        }
        pluginPO.setContext(context);
        pluginPO.setExtra(extra);
        pluginPO.setUpdateTime(new Date());
        int frist = pluginMapper.updateByPrimaryKey(pluginPO);

        List<AppPluChPO> appPluChPOs = appPluChMapper.queryByIds(pluginId, appId);
        Map<Integer, AppPluChPO> map = new HashMap<>(appPluChPOs.size());
        for (AppPluChPO po : appPluChPOs) {
            map.put(po.getSoftChannelId(), po);
        }
        List<AppPluChPO> insApcPOs = new ArrayList<>();
        for (int scId : softChannel) {
            if (map.containsKey(scId)) {
                map.remove(scId);
            } else {
                AppPluChPO po = new AppPluChPO();
                po.setAppId(appId);
                po.setSoftChannelId(scId);
                po.setPluginId(pluginId);
                po.setStatus(pluginPO.getStatus());
                po.setCreateTime(new Date());
                insApcPOs.add(po);
            }
        }

        int secend = 0;
        if (insApcPOs.size() != 0) {
            secend = appPluChMapper.batchInsert(insApcPOs);
        }

        int third = 0;
        if (map.size() != 0) {
            List<Integer> delApcIds = new ArrayList<>();
            for (AppPluChPO po : map.values()) {
                delApcIds.add(po.getApcId());
            }
            third = appPluChMapper.batchDelete(delApcIds);
        }

        this.deleteRedis();
        return frist + secend + third;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int updateStatus(int pluginId, byte status) {
        PluginPO pluginPO = pluginMapper.selectByPrimaryKey(pluginId);
        pluginPO.setPublishTime(status == 2 ? new Date() : null);
        pluginPO.setStatus(status);
        int frist = pluginMapper.updateByPrimaryKey(pluginPO);

        int secend = appPluChMapper.updateStatus(pluginId, status);

        this.deleteRedis();
        return frist + secend;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(int pluginId) {
        int frist = appPluChMapper.deleteByAppId(pluginId);
        // 插件表进行假删除
        int secend = pluginMapper.deleteByPrimaryKey(pluginId);

        this.deleteRedis();
        return frist + secend;
    }

    /**
     * 根据上传文件更新PluginPO
     *
     * @param file     文件
     * @param pluginPO 插件PO
     */
    private void setPluginPObyFile(MultipartFile file, PluginPO pluginPO) {
        pluginPO.setUrl(FileUtil.uploadFile(file, pluginDir, "plugin"));
        pluginPO.setSize((int) file.getSize());
        try {
            pluginPO.setMd5(DigestUtils.md5DigestAsHex(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除应用更新对应的Redis
     */
    private void deleteRedis() {
        Set<String> redisKeys = template.keys(RedisKeyUtil.genPluginRedisKey("*"));
        if (!CollectionUtils.isEmpty(redisKeys)) {
            template.delete(redisKeys);
        }
    }
}