package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.AppBO;
import com.zfs.common.mapper.*;
import com.zfs.common.pojo.AdChannelPO;
import com.zfs.common.pojo.AppChPO;
import com.zfs.common.pojo.AppPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.service.IAppService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.web.utils.FileUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.vo.AppVO;
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
    private final static Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);


    @Resource
    private AppMapper appMapper;
    @Resource
    private AppChMapper appChMapper;
    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private AdChannelMapper adChannelMapper;
    @Autowired
    private AdconfigMapper adconfigMapper;
    @Autowired
    private SoftChannelMapper softChannelMapper;
    @Value("${file.appDir}")
    private String appDir;

    @Override
    public ResultVO query(int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Integer> appIds = appMapper.queryAppId(reqData);

        List<AppBO> bos = new ArrayList<>();
        if(null != appIds && appIds.size() > 0) {
            bos = appMapper.queryByIds(appIds);
        }
        List<AppVO> dto = AppVO.convert(bos);
        return new ResultVO(1000,new PageInfoVO<>(page.getTotal(), dto));
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
                int result = appChMapper.batchInsert(insAppChPOs);
                if (result == 0) {
                    LogUtil.log(logger, "insert", "新增应用渠道失败", insAppChPOs);
                }
            }
            /***
             * 更新t_ad_channel广告渠道
             * 有数据则更新type和update_time字段
             * 没有数据则将广告id和渠道id组合之后和app_id关联
             * */
            List<AdChannelPO> adChannelPOS=adChannelMapper.queryByAppId(appPO.getAppId());
            if(adChannelPOS!=null&&adChannelPOS.size()>0){
                adChannelMapper.batchUpdate(adChannelPOS);
            }else{
                int appID=appPO.getAppId();
                List<Integer> adIDS=adconfigMapper.queryIDS();
                List<Integer> softIDS=softChannelMapper.queryIDS();
                boolean b1=adIDS!=null&&adIDS.size()>0;
                boolean b2=softIDS!=null&&softIDS.size()>0;
                if(b1&&b2){
                    List<AdChannelPO> insertAdChannelPOS=new ArrayList<>();
                    for(Integer adID:adIDS){
                        for(Integer softID:softIDS){
                            AdChannelPO po=new AdChannelPO();
                            buildAdChannelPO(po,adID,softID,appID);
                            insertAdChannelPOS.add(po);
                        }
                    }
                    adChannelMapper.batchInsert(insertAdChannelPOS);
                }else{
                    logger.info("t_adconfig和t_soft_channel不存在数据");
                }
            }
            this.deleteRedis();
            return new ResultVO(1000);
        }

        // 新增
        appPO = new AppPO();
        buildAppVO(file, updateType, context, extra, aId, apkInfo, appPO);

        int result1 = appMapper.insert(appPO);
        if (result1 == 0) {
            LogUtil.log(logger, "insert", "新增appPO失败", appPO);
        }

        List<AppChPO> appChPOs = new ArrayList<>();
        for (int chanId : softChannel) {
            AppChPO appChPO = new AppChPO();
            appChPO.setAppId(appPO.getAppId());
            appChPO.setSoftChannelId(chanId);
            appChPO.setStatus((byte) 1);
            appChPOs.add(appChPO);
        }

        int result2 = appChMapper.batchInsert(appChPOs);
        if (result2 == 0) {
            LogUtil.log(logger, "insert", "新增appChPOs失败", appChPOs);
        }
        /***
         * 更新t_ad_channel广告渠道
         * 有数据则更新type和update_time字段
         * 没有数据则将广告id和渠道id组合之后和app_id关联
         * */
        List<AdChannelPO> adChannelPOS=adChannelMapper.queryByAppId(appPO.getAppId());
        if(adChannelPOS!=null&&adChannelPOS.size()>0){
            adChannelMapper.batchUpdate(adChannelPOS);
        }else{
            int appID=appPO.getAppId();
            List<Integer> adIDS=adconfigMapper.queryIDS();
            List<Integer> softIDS=softChannelMapper.queryIDS();
            boolean b1=adIDS!=null&&adIDS.size()>0;
            boolean b2=softIDS!=null&&softIDS.size()>0;
            if(b1&&b2){
                List<AdChannelPO> insertAdChannelPOS=new ArrayList<>();
                for(Integer adID:adIDS){
                    for(Integer softID:softIDS){
                        AdChannelPO po=new AdChannelPO();
                        buildAdChannelPO(po,adID,softID,appID);
                        insertAdChannelPOS.add(po);
                    }
                }
                adChannelMapper.batchInsert(insertAdChannelPOS);
            }else{
                logger.info("t_adconfig和t_soft_channel不存在数据");
            }
        }
        this.deleteRedis();
        return new ResultVO(1000);
    }

    private void buildAdChannelPO(AdChannelPO po, Integer adID, Integer softID, int appID) {
        po.setAdId(adID);
        po.setSoftChannelId(softID);
        po.setCreateTime(new Date());
        po.setType((byte) 2);//开启广告
        po.setAppId(appID);
        po.setDr((byte) 1);//不删除
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
        if (frist == 0) {
            LogUtil.log(logger, "update", "更新appChPOs失败", appPO);
        }

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
            if (secend == 0) {
                LogUtil.log(logger, "update", "新增应用渠道数据失败", insAppChPOs);
            }
        }

        int third = 0;
        if (map.size() != 0) {
            // 待删除的应用渠道数据
            List<Integer> delAcIds = new ArrayList<>();
            for (AppChPO po : map.values()) {
                delAcIds.add(po.getAcId());
            }
            third = appChMapper.batchDelete(delAcIds);
            if (third == 0) {
                LogUtil.log(logger, "update", "删除delAcIds失败", delAcIds);
            }
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
        if (frist == 0) {
            LogUtil.log(logger, "updateStatus", "删除appPO失败", appPO);
        }

        int secend = appChMapper.updateStatus(appId, status);
        if (secend == 0) {
            LogUtil.log(logger, "updateStatus", "更新失败", appId, status);
        }

        this.deleteRedis();

        return frist + secend;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(int appId) {
        int frist = appChMapper.deleteByAppId(appId);
        if (frist == 0) {
            LogUtil.log(logger, "delete", "删除失败", appId);
        }
        // 应用表进行假删除
        int secend = appMapper.deleteByPrimaryKey(appId);
        if (secend == 0) {
            LogUtil.log(logger, "delete", "应用表假删除失败", appId);
        }

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
        //删除配置开启广告的缓存
        Set<String> redisAdConfigKeys = template.keys(RedisKeyUtil.genAdconfigRedisKey("*"));
        if (!CollectionUtils.isEmpty(redisKeys)) {
            template.delete(redisAdConfigKeys);
        }
    }
}
