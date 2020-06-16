package com.rpa.web.service.impl;

import com.rpa.common.bo.AvatarBO;
import com.rpa.common.constant.ModuleConstant;
import com.rpa.common.mapper.AppAvaChMapper;
import com.rpa.common.mapper.AvatarMapper;
import com.rpa.common.pojo.AppAvaChPO;
import com.rpa.common.pojo.AvatarPO;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.common.PageHelper;
import com.rpa.web.service.IAvatarService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.FileUtil;
import com.rpa.web.vo.AvatarVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/12/12 19:30
 * @description: 分身更新
 * @version: 1.0
 */
@Service
public class AvatarServiceImpl implements IAvatarService {
    private final static Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    @Resource
    private AvatarMapper avatarMapper;
    @Resource
    private AppAvaChMapper appAvaChMapper;
    @Autowired
    private StringRedisTemplate template;

    @Value("${file.avatarDir}")
    private String avatarDir;

    @Override
    public DTPageInfo<AvatarVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        PageHelper.startPage(pageNum, pageSize);
        List<Integer> avatarIds = avatarMapper.queryAvatarIds(reqData);

        List<AvatarBO> bos = new ArrayList<>();
        if (null != avatarIds && avatarIds.size() > 0) {
            bos = avatarMapper.queryByAvatarIds(avatarIds);
        }
        List<AvatarVO> dto = AvatarVO.convert(bos);
        return new DTPageInfo<>(draw, dto.size(), dto);
    }

    @Override
    public List<AvatarVO> queryById(long avatarId) {
        List<AvatarBO> pos = avatarMapper.queryByAvatarId(avatarId);
        return AvatarVO.convert(pos);
    }

    @Override
    public List<Integer> queryChanIds(long avatarId, int appId) {
        List<AppAvaChPO> aacPOs = appAvaChMapper.queryByAvatarIdAndAppId(avatarId, appId);

        List<Integer> chanIds = new ArrayList<>();
        for (AppAvaChPO po : aacPOs) {
            chanIds.add(po.getSoftChannelId());
        }

        return chanIds;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO insert(MultipartFile file, int osVersion,byte updateType, int appId, int[] softChannel, String context, String extra,
                           int aId) {
        // 解析Apk
        Map<String, Object> apkInfo = FileUtil.resolveApk(file, avatarDir, ModuleConstant.AVATAR);
        if (apkInfo.get("channel") == null || !"vbooster".equals(apkInfo.get("channel"))) {
            // 上传应用非官方渠道！
            return new ResultVO(1103);
        }
        // 查询是否已存在
        AvatarPO avatarPO = avatarMapper.queryByVersionCode(apkInfo.get("versioncode"),osVersion);
        if (null == avatarPO) {
            // 新增
            avatarPO = new AvatarPO();
            this.buildAvatarPO(file, updateType, context, extra, apkInfo, avatarPO, aId);
            avatarPO.setOsVersion(osVersion);
            avatarMapper.insert(avatarPO);

            List<AppAvaChPO> aacPOs = new ArrayList<>();
            for (int chanId : softChannel) {
                aacPOs.add(genAacPO(appId, chanId, avatarPO.getAvatarId()));
            }
            int result = appAvaChMapper.batchInsert(aacPOs);
            if (result == 0) {
                LogUtil.log(logger, "insert", "新增失败", aacPOs);
            }

            this.deleteRedis();
            return new ResultVO(1000);
        }

        // 更新
        this.buildAvatarPO(file, updateType, context, extra, apkInfo, avatarPO, aId);
        avatarMapper.updateByPrimaryKey(avatarPO);
        // 根据avatarId和appId查询应用渠道数据
        List<AppAvaChPO> aacPOs = appAvaChMapper.queryByAvatarIdAndAppId(avatarPO.getAvatarId(), appId);
        List<Integer> exitChanIds = new ArrayList<>();
        if (null != aacPOs && aacPOs.size() > 0) {
            for (AppAvaChPO po : aacPOs) {
                exitChanIds.add(po.getSoftChannelId());
            }
        }

        // 待新增的AppChPO
        List<AppAvaChPO> insAppChPOs = new ArrayList<>();
        for (int chanId : softChannel) {
            if (exitChanIds.size() == 0 || !exitChanIds.contains(chanId)) {
                insAppChPOs.add(genAacPO(appId, chanId, avatarPO.getAvatarId()));
            }
        }

        if (insAppChPOs.size() != 0) {
            // 新增应用渠道数据
            int result = appAvaChMapper.batchInsert(insAppChPOs);
            if (result == 0) {
                LogUtil.log(logger, "insert", "新增应用渠道数据失败", insAppChPOs);
            }
        }

        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO update(long avatarId, MultipartFile file, byte updateType, int appId, int[] softChannel, String context, String extra) {
        AvatarPO avatarPO = avatarMapper.selectByPrimaryKey(avatarId);
        if (file != null && !"".equals(file)) {
            // 解析Apk
            Map<String, Object> apkInfo = FileUtil.resolveApk(file, avatarDir, ModuleConstant.AVATAR);
            if (apkInfo.get("channel") == null || !"vbooster".equals(apkInfo.get("channel"))) {
                // 上传应用非官方渠道！
                return new ResultVO(1103);
            }

            buildAvatarPO(file, updateType, context, extra, apkInfo, avatarPO, 0);
        }
        avatarPO.setUpdateType(updateType);
        avatarPO.setContext(context);
        avatarPO.setExtra(extra);
        avatarPO.setUpdateTime(new Date());
        int result1 = avatarMapper.updateByPrimaryKey(avatarPO);
        if (result1 == 0) {
            LogUtil.log(logger, "update", "更新avatarPO失败", avatarPO);
        }
        // 根据appId查询应用渠道数据
        List<AppAvaChPO> aacPOs = appAvaChMapper.queryByAvatarIdAndAppId(avatarId, appId);
        List<AppAvaChPO> insAacPOs = new ArrayList<>();

        Map<Integer, AppAvaChPO> exitAacMap = new HashMap<>(aacPOs.size());
        for (AppAvaChPO aacPO : aacPOs) {
            exitAacMap.put(aacPO.getSoftChannelId(), aacPO);
        }

        for (int chanId : softChannel) {
            if (exitAacMap.containsKey(chanId)) {
                exitAacMap.remove(chanId);
            } else {
                insAacPOs.add(genAacPO(appId, chanId, avatarPO.getAvatarId()));
            }
        }

        if (insAacPOs.size() != 0) {
            // 新增应用渠道数据
            int result2 = appAvaChMapper.batchInsert(insAacPOs);
            if (result2 == 0) {
                LogUtil.log(logger, "update", "插入insAacPOs失败", insAacPOs);
            }
        }

        if (exitAacMap.size() != 0) {
            // 待删除的应用渠道数据
            List<Long> delAacIds = new ArrayList<>();
            for (AppAvaChPO po : exitAacMap.values()) {
                delAacIds.add(po.getAacId());
            }
            int result3 = appAvaChMapper.batchDelete(delAacIds);
            if (result3 == 0) {
                LogUtil.log(logger, "update", "删除delAacIds失败", delAacIds);
            }
        }

        this.deleteRedis();
        return new ResultVO(1000);
    }

    /**
     * 生成 AppAvaChPO
     *
     * @param appId
     * @param chanId
     * @param avatarId
     * @return
     */
    private AppAvaChPO genAacPO(int appId, int chanId, long avatarId) {
        AppAvaChPO aacPO = new AppAvaChPO();
        aacPO.setAppId(appId);
        aacPO.setSoftChannelId(chanId);
        aacPO.setAvatarId(avatarId);
        aacPO.setStatus((byte) 1);
        aacPO.setCreateTime(new Date());
        return aacPO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO updateStatus(long avatarId, byte status) {
        AvatarPO avatarPO = avatarMapper.selectByPrimaryKey(avatarId);
        avatarPO.setPublishTime(status == 2 ? new Date() : null);
        avatarPO.setStatus(status);
        avatarMapper.updateByPrimaryKey(avatarPO);
        int result = appAvaChMapper.updateStatus(avatarId, status);
        if (result == 0) {
            LogUtil.log(logger, "updateStatus", "更新状态失败", avatarId, status);
        }

        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO delete(long avatarId) {
        int result1 = appAvaChMapper.deleteByAvatarId(avatarId);
        if (result1 == 0) {
            LogUtil.log(logger, "delete", "删除失败", avatarId, avatarId);
        }
        // 应用表进行假删除
        int result2 = avatarMapper.deleteByPrimaryKey(avatarId);
        if (result2 == 0) {
            LogUtil.log(logger, "delete", "应用表进行假删除失败", avatarId);
        }

        this.deleteRedis();
        return new ResultVO(1000);
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
     * @param avatarPO
     */
    private void buildAvatarPO(MultipartFile file, byte updateType, String context, String extra, Map<String, Object> apkInfo, AvatarPO avatarPO, int aId) {

        avatarPO.setVersionName((String) apkInfo.get("versionname"));
        avatarPO.setVersionCode(Math.toIntExact((Long) apkInfo.get("versioncode")));
        avatarPO.setUrl((String) apkInfo.get("url"));
        avatarPO.setUpdateType(updateType);
        avatarPO.setSize((int) file.getSize());
        avatarPO.setExtra(extra);
        avatarPO.setContext(context);
        try {
            avatarPO.setMd5(DigestUtils.md5DigestAsHex(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (0 == aId) {
            // 更新
            avatarPO.setUpdateTime(new Date());
        } else {
            // 插入
            avatarPO.setStatus((byte) 1);
            avatarPO.setaId(aId);
            avatarPO.setCreateTime(new Date());
            avatarPO.setDr((byte) 1);
        }
    }

    /**
     * 删除对应的Redis
     */
    private void deleteRedis() {
        Set<String> redisKeys = template.keys(RedisKeyUtil.genAvatarRedisKey("*"));
        if (!CollectionUtils.isEmpty(redisKeys)) {
            template.delete(redisKeys);
        }
    }
}
