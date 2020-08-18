package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.PluginBO;
import com.zfs.common.mapper.AppPluChMapper;
import com.zfs.common.mapper.PluginMapper;
import com.zfs.common.pojo.AppPluChPO;
import com.zfs.common.pojo.PluginPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.utils.FileUtil;
import com.zfs.web.vo.PluginVO;
import com.zfs.web.service.IPluginService;
import com.zfs.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.File;
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
    private final static Logger logger = LoggerFactory.getLogger(PluginServiceImpl.class);

    @Resource
    private PluginMapper pluginMapper;
    @Resource
    private AppPluChMapper appPluChMapper;
    @Autowired
    private StringRedisTemplate template;



    @Override
    public ResultVO query(int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<PluginBO> pos = pluginMapper.query(reqData);
        List<PluginVO> dtos = PluginVO.convert(pos);
        return new ResultVO(1000,new PageInfoVO<>(page.getTotal(), dtos));
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
    public ResultVO insert(String file, int appId, int[] softChannel, String context, String extra, int aId) {
        PluginPO pluginPO = new PluginPO();
        this.setPluginPObyFile(file, pluginPO);

        pluginPO.setStatus((byte) 1);
        pluginPO.setContext(context);
        pluginPO.setExtra(extra);
        pluginPO.setaId(aId);
        pluginPO.setCreateTime(new Date());
        pluginPO.setDr((byte) 1);

        int result1 = pluginMapper.insert(pluginPO);
        if (result1 == 0) {
            LogUtil.log(logger, "insert", "插入pluginPO失败", pluginPO);
        }

        List<AppPluChPO> appPluChPOs = new ArrayList<>();
        for (int chanId : softChannel) {
            AppPluChPO appPluChPO = new AppPluChPO();

            appPluChPO.setAppId(appId);//支持版本
            appPluChPO.setSoftChannelId(chanId);//支持渠道
            appPluChPO.setPluginId(pluginPO.getPluginId());//插件
            appPluChPO.setCreateTime(new Date());
            appPluChPO.setStatus((byte) 1);

            appPluChPOs.add(appPluChPO);
        }

        int result2 = appPluChMapper.batchInsert(appPluChPOs);
        if (result2 == 0) {
            LogUtil.log(logger, "insert", "插入appPluChPOs失败", appPluChPOs);
        }

        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int update(int pluginId, String file, int appId, int[] softChannel, String context, String extra) {
        PluginPO pluginPO = pluginMapper.selectByPrimaryKey(pluginId);
        if (file != null && !"".equals(file)) {
            this.setPluginPObyFile(file, pluginPO);
        }
        pluginPO.setContext(context);
        pluginPO.setExtra(extra);
        pluginPO.setUpdateTime(new Date());
        int frist = pluginMapper.updateByPrimaryKey(pluginPO);
        if (frist == 0) {
            LogUtil.log(logger, "update", "更新pluginPO失败", pluginPO);
        }

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
            if (secend == 0) {
                LogUtil.log(logger, "update", "插入insApcPOs失败", insApcPOs);
            }
        }

        int third = 0;
        if (map.size() != 0) {
            List<Integer> delApcIds = new ArrayList<>();
            for (AppPluChPO po : map.values()) {
                delApcIds.add(po.getApcId());
            }
            third = appPluChMapper.batchDelete(delApcIds);
            if (third == 0) {
                LogUtil.log(logger, "update", "删除delApcIds失败", delApcIds);
            }
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
        if (frist == 0) {
            LogUtil.log(logger, "updateStatus", "更新pluginPO失败", pluginPO);
        }

        int secend = appPluChMapper.updateStatus(pluginId, status);
        if (secend == 0) {
            LogUtil.log(logger, "updateStatus", "更新pluginId, status失败", pluginId, status);
        }

        this.deleteRedis();
        return frist + secend;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(int pluginId) {
        int frist = appPluChMapper.deleteByAppId(pluginId);
        if (frist == 0) {
            LogUtil.log(logger, "delete", "删除pluginId失败", pluginId);
        }

        // 插件表进行假删除
        int secend = pluginMapper.deleteByPrimaryKey(pluginId);
        if (secend == 0) {
            LogUtil.log(logger, "delete", "插件表进行假删除pluginId失败", pluginId);
        }

        this.deleteRedis();
        return frist + secend;
    }

    /**
     * 根据上传文件更新PluginPO
     *
     * @param tmp     文件名，相对路径
     * @param pluginPO 插件PO
     */
    private void setPluginPObyFile(String tmp, PluginPO pluginPO) {
        pluginPO.setUrl(tmp);
        File file=new File(FileUtil.rootPath+tmp);
        pluginPO.setSize((int) file.length());
        try {
            String s = DigestUtils.md5DigestAsHex(com.zfs.common.utils.FileUtil.readFile(FileUtil.rootPath + tmp));
            pluginPO.setMd5(s);
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
