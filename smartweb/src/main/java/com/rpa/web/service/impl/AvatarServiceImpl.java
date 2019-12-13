package com.rpa.web.service.impl;

import com.rpa.common.bo.AvatarBO;
import com.rpa.common.constant.ModuleConstant;
import com.rpa.common.mapper.AppAvaChMapper;
import com.rpa.common.mapper.AvatarMapper;
import com.rpa.common.pojo.AppAvaChPO;
import com.rpa.common.pojo.AvatarPO;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.common.PageHelper;
import com.rpa.web.service.IAvatarService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.FileUtil;
import com.rpa.web.vo.AvatarVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Resource
    private AvatarMapper avatarMapper;
    @Resource
    private AppAvaChMapper appAvaChMapper;

    @Value("${file.avatarDir}")
    private String avatarDir;

    @Override
    public DTPageInfo<AvatarVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        PageHelper.startPage(pageNum, pageSize);
        return null;
//        List<Integer> appIds = avatarMapper.queryAppId(reqData);
//
//        List<AppBO> bos = new ArrayList<>();
//        if (null != appIds && appIds.size() > 0) {
//            bos = avatarMapper.queryByIds(appIds);
//        }
//        List<AvatarVO> dto = AvatarVO.convert(bos);
//        return new DTPageInfo<>(draw, dto.size(), dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO insert(MultipartFile file, byte updateType, int[] softChannel, String context, String extra,
                           int aId) {
//        // 解析Apk
//        Map<String, Object> apkInfo = FileUtil.resolveApk(file, avatarDir, ModuleConstant.AVATAR);
//        if (apkInfo.get("channel") == null || !"vbooster".equals(apkInfo.get("channel"))) {
//            // 上传应用非官方渠道！
//            return new ResultVO(1103);
//        }
//
//        // 查询是否已存在
//        AvatarPO appPO = avatarMapper.queryByVersionCode(apkInfo.get("versioncode"));
//        if (appPO != null) {
//            // 更新
//            buildAppVO(file, updateType, context, extra, aId, apkInfo, appPO);
//            avatarMapper.updateByPrimaryKey(appPO);
//            // 根据appId查询应用渠道数据
//            List<AppAvaChPO> appChPOs = appAvaChMapper.queryByAppId(appPO.getAppId());
//
//            Map<Integer, AppAvaChPO> map = new HashMap<>(appChPOs.size());
//            for (AppAvaChPO appChPO : appChPOs) {
//                map.put(appChPO.getSoftChannelId(), appChPO);
//            }
//            // 待新增的AppChPO
//            List<AppAvaChPO> insAppChPOs = new ArrayList<>();
//            for (int scId : softChannel) {
//                if (!map.containsKey(scId)) {
//                    AppAvaChPO appChPO = new AppAvaChPO();
//                    appChPO.setStatus(appPO.getStatus().byteValue());
//                    appChPO.setAppId(appPO.getAppId());
//                    appChPO.setSoftChannelId(scId);
//                    insAppChPOs.add(appChPO);
//                }
//            }
//
//            if (insAppChPOs.size() != 0) {
//                // 新增应用渠道数据
//                appAvaChMapper.batchInsert(insAppChPOs);
//            }
//
//            return new ResultVO(1000);
//        }
//
//        // 新增
//        appPO = new AvatarPO();
//        buildAppVO(file, updateType, context, extra, aId, apkInfo, appPO);
//
//        avatarMapper.insert(appPO);
//
//        List<AppAvaChPO> appChPOs = new ArrayList<>();
//        for (int chanId : softChannel) {
//            AppAvaChPO appChPO = new AppAvaChPO();
//            appChPO.setAppId(appPO.getAppId());
//            appChPO.setSoftChannelId(chanId);
//            appChPO.setStatus((byte) 1);
//            appChPOs.add(appChPO);
//        }
//
//        appAvaChMapper.batchInsert(appChPOs);

        return new ResultVO(1000);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO update(long avatarId, MultipartFile file, byte updateType, int[] softChannel, String context, String extra) {
//        AvatarPO appPO = avatarMapper.selectByPrimaryKey(avatarId);
//        if (file != null && !"".equals(file)) {
//            // 根据url对应的apk获取版本名称、版本号、文件大小
//            this.setAppPObyFile(file, appPO);
//        }
//        appPO.setUpdateType(updateType);
//        appPO.setContext(context);
//        appPO.setExtra(extra);
//        appPO.setUpdateTime(new Date());
//        avatarMapper.updateByPrimaryKey(appPO);
//
//        // 根据appId查询应用渠道数据
//        List<AppAvaChPO> appChPOs = appAvaChMapper.queryByAppId(avatarId);
//        // 待新增的AppChPO
//        List<AppAvaChPO> insAppChPOs = new ArrayList<>();
//
//        Map<Integer, AppAvaChPO> map = new HashMap<>(appChPOs.size());
//        for (AppAvaChPO appChPO : appChPOs) {
//            map.put(appChPO.getSoftChannelId(), appChPO);
//        }
//
//        for (int scId : softChannel) {
//            if (map.containsKey(scId)) {
//                map.remove(scId);
//            } else {
//                AppAvaChPO appChPO = new AppAvaChPO();
//                appChPO.setStatus(appPO.getStatus().byteValue());
//                appChPO.setAppId(avatarId);
//                appChPO.setSoftChannelId(scId);
//                insAppChPOs.add(appChPO);
//            }
//        }
//
//        if (insAppChPOs.size() != 0) {
//            // 新增应用渠道数据
//            appAvaChMapper.batchInsert(insAppChPOs);
//        }
//
//        if (map.size() != 0) {
//            // 待删除的应用渠道数据
//            List<Integer> delAcIds = new ArrayList<>();
//            for (AppAvaChPO po : map.values()) {
//                delAcIds.add(po.getAcId());
//            }
//            appAvaChMapper.batchDelete(delAcIds);
//        }

        return new ResultVO(1000);
    }

    private void setAppPObyFile(MultipartFile file, AvatarPO appPO) {
        Map<String, Object> apkInfo = FileUtil.resolveApk(file, avatarDir, "app");
        appPO.setUrl((String) apkInfo.get("url"));
        appPO.setVersionName((String) apkInfo.get("versionname"));
        appPO.setVersionCode(Math.toIntExact((Long) apkInfo.get("versioncode")));
        appPO.setSize((int) file.getSize());

        try {
            appPO.setMd5(DigestUtils.md5DigestAsHex(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO updateStatus(long avatarId, byte status) {
//        AvatarPO appPO = avatarMapper.selectByPrimaryKey(avatarId);
//        appPO.setPublishTime(status == 2 ? new Date() : null);
//        appPO.setStatus(status);
//        avatarMapper.updateByPrimaryKey(appPO);
//        appAvaChMapper.updateStatus(avatarId, status);

        return new ResultVO(1000);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO delete(long avatarId) {
//        appAvaChMapper.deleteByAppId(avatarId);
//        // 应用表进行假删除
//        avatarMapper.deleteByPrimaryKey(avatarId);

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
     * @param appPO
     */
    private void buildAppVO(MultipartFile file, byte updateType, String context, String extra, int aId, Map<String, Object> apkInfo, AvatarPO appPO) {
        appPO.setUrl((String) apkInfo.get("url"));
        appPO.setVersionName((String) apkInfo.get("versionname"));
        appPO.setVersionCode(Math.toIntExact((Long) apkInfo.get("versioncode")));
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
        appPO.setStatus((byte) 1);
        appPO.setDr((byte) 1);
    }
}
