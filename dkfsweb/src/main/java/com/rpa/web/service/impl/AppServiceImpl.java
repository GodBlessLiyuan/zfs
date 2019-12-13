package com.rpa.web.service.impl;

import com.rpa.common.bo.AppBO;
import com.rpa.common.mapper.AppChMapper;
import com.rpa.common.mapper.AppMapper;
import com.rpa.common.pojo.AppChPO;
import com.rpa.common.pojo.AppPO;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.web.service.IAppService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.FileUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.vo.AppVO;
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
 * @date: Created in 2019/10/1 13:49
 * @description: 版本更新
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class AppServiceImpl implements IAppService {

    @Resource
    private AppMapper appMapper;
    @Resource
    private AppChMapper appChMapper;
    @Autowired
    private StringRedisTemplate template;

    @Value("${file.appDir}")
    private String appDir;

    @Override
    public DTPageInfo<AppVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        PageHelper.startPage(pageNum, pageSize);
        List<Integer> appIds = appMapper.queryAppId(reqData);

        List<AppBO> bos = new ArrayList<>();
        if(null != appIds && appIds.size() > 0) {
            bos = appMapper.queryByIds(appIds);
        }
        List<AppVO> dto = AppVO.convert(bos);
        return new DTPageInfo<>(draw, dto.size(), dto);
    }

    @Override
    public List<AppVO> queryAll() {
        List<AppBO> pos = appMapper.queryAll();
        return AppVO.convert(pos);
    }

    @Override
    public List<AppVO> queryById(int appId) {
        List<AppBO> pos = appMapper.queryById(appId);
        return AppVO.convert(pos);
    }

    @Transactional(rollbackFor = {})
    @Override
    public ResultVO insert(MultipartFile file, byte updateType, int[] softChannel, String context, String extra,
                           int aId) {
        // 解析Apk
        Map<String, Object> apkInfo = FileUtil.resolveApk(file, appDir, "app");
        if (apkInfo.get("channel") == null || !"vbooster".equals(apkInfo.get("channel"))) {
            // 上传应用非官方渠道！
            return new ResultVO(1103);
        }

        // 查询是否已存在
        AppPO appPO = appMapper.queryByVersionCode(apkInfo.get("versioncode"));
        if (appPO != null) {
            // 更新
            buildAppVO(file, updateType, context, extra, aId, apkInfo, appPO);
            appMapper.updateByPrimaryKey(appPO);
            // 根据appId查询应用渠道数据
            List<AppChPO> appChPOs = appChMapper.queryByAppId(appPO.getAppId());

            Map<Integer, AppChPO> map = new HashMap<>(appChPOs.size());
            for (AppChPO appChPO : appChPOs) {
                map.put(appChPO.getSoftChannelId(), appChPO);
            }
            // 待新增的AppChPO
            List<AppChPO> insAppChPOs = new ArrayList<>();
            for (int scId : softChannel) {
                if (!map.containsKey(scId)) {
                    AppChPO appChPO = new AppChPO();
                    appChPO.setStatus(appPO.getStatus().byteValue());
                    appChPO.setAppId(appPO.getAppId());
                    appChPO.setSoftChannelId(scId);
                    insAppChPOs.add(appChPO);
                }
            }

            if (insAppChPOs.size() != 0) {
                // 新增应用渠道数据
                appChMapper.batchInsert(insAppChPOs);
            }

            return new ResultVO(1000);
        }

        // 新增
        appPO = new AppPO();
        buildAppVO(file, updateType, context, extra, aId, apkInfo, appPO);

        appMapper.insert(appPO);

        List<AppChPO> appChPOs = new ArrayList<>();
        for (int chanId : softChannel) {
            AppChPO appChPO = new AppChPO();
            appChPO.setAppId(appPO.getAppId());
            appChPO.setSoftChannelId(chanId);
            appChPO.setStatus((byte) 1);
            appChPOs.add(appChPO);
        }

        appChMapper.batchInsert(appChPOs);

        this.deleteRedis();

        return new ResultVO(1000);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int update(int appId, MultipartFile file, byte updateType, int[] softChannel, String context, String extra) {
        AppPO appPO = appMapper.selectByPrimaryKey(appId);
        if (file != null && !"".equals(file)) {
            // 根据url对应的apk获取版本名称、版本号、文件大小
            this.setAppPObyFile(file, appPO);
        }
        appPO.setUpdateType(updateType);
        appPO.setContext(context);
        appPO.setExtra(extra);
        appPO.setUpdateTime(new Date());
        int frist = appMapper.updateByPrimaryKey(appPO);

        // 根据appId查询应用渠道数据
        List<AppChPO> appChPOs = appChMapper.queryByAppId(appId);
        // 待新增的AppChPO
        List<AppChPO> insAppChPOs = new ArrayList<>();

        Map<Integer, AppChPO> map = new HashMap<>(appChPOs.size());
        for (AppChPO appChPO : appChPOs) {
            map.put(appChPO.getSoftChannelId(), appChPO);
        }

        for (int scId : softChannel) {
            if (map.containsKey(scId)) {
                map.remove(scId);
            } else {
                AppChPO appChPO = new AppChPO();
                appChPO.setStatus(appPO.getStatus().byteValue());
                appChPO.setAppId(appId);
                appChPO.setSoftChannelId(scId);
                insAppChPOs.add(appChPO);
            }
        }

        int secend = 0;
        if (insAppChPOs.size() != 0) {
            // 新增应用渠道数据
            secend = appChMapper.batchInsert(insAppChPOs);
        }

        int third = 0;
        if (map.size() != 0) {
            // 待删除的应用渠道数据
            List<Integer> delAcIds = new ArrayList<>();
            for (AppChPO po : map.values()) {
                delAcIds.add(po.getAcId());
            }
            third = appChMapper.batchDelete(delAcIds);
        }

        this.deleteRedis();

        return frist + secend + third;
    }

    private void setAppPObyFile(MultipartFile file, AppPO appPO) {
        Map<String, Object> apkInfo = FileUtil.resolveApk(file, appDir, "app");
        appPO.setUrl((String) apkInfo.get("url"));
        appPO.setVersionname((String) apkInfo.get("versionname"));
        appPO.setVersioncode(Math.toIntExact((Long) apkInfo.get("versioncode")));
        appPO.setSize((int) file.getSize());

        try {
            appPO.setMd5(DigestUtils.md5DigestAsHex(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = {})
    @Override
    public int updateStatus(int appId, int status) {
        AppPO appPO = appMapper.selectByPrimaryKey(appId);
        appPO.setPublishTime(status == 2 ? new Date() : null);
        appPO.setStatus(status);
        int frist = appMapper.updateByPrimaryKey(appPO);

        int secend = appChMapper.updateStatus(appId, status);

        this.deleteRedis();

        return frist + secend;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(int appId) {
        int frist = appChMapper.deleteByAppId(appId);
        // 应用表进行假删除
        int secend = appMapper.deleteByPrimaryKey(appId);

        this.deleteRedis();

        return frist + secend;
    }

    /**
     * 构建AppVO
     *
     * @param file
     * @param updateType
     * @param context
     * @param extra
     * @param aId
     * @param apkInfo
     * @param appPO
     */
    private void buildAppVO(MultipartFile file, byte updateType, String context, String extra, int aId, Map<String, Object> apkInfo, AppPO appPO) {
        appPO.setUrl((String) apkInfo.get("url"));
        appPO.setVersionname((String) apkInfo.get("versionname"));
        appPO.setVersioncode(Math.toIntExact((Long) apkInfo.get("versioncode")));
        appPO.setSize((int) file.getSize());

        try {
            appPO.setMd5(DigestUtils.md5DigestAsHex(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        appPO.setUpdateType(updateType);
        appPO.setContext(context);
        appPO.setExtra(extra);

        appPO.setCreateTime(new Date());
        appPO.setaId(aId);
        appPO.setStatus(1);
        appPO.setDr((byte) 1);
    }

    /**
     * 删除应用更新对应的Redis
     */
    private void deleteRedis() {
        Set<String> redisKeys = template.keys(RedisKeyUtil.genAppRedisKey("*"));
        if (!CollectionUtils.isEmpty(redisKeys)) {
            template.delete(redisKeys);
        }
    }
}