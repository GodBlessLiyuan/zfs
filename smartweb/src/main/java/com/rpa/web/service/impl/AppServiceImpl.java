package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.AppDTO;
import com.rpa.web.mapper.AppChMapper;
import com.rpa.web.mapper.AppMapper;
import com.rpa.web.pojo.AppChPO;
import com.rpa.web.pojo.AppPO;
import com.rpa.web.service.IAppService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Override
    public DTPageInfo<AppDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<AppPO> page = PageHelper.startPage(pageNum, pageSize);
        List<AppPO> pos = appMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), AppDTO.convert(pos));
    }

    @Override
    public List<AppDTO> queryAll() {
        List<AppPO> pos = appMapper.queryAll();
        return AppDTO.convert(pos);
    }

    @Override
    public List<AppDTO> queryById(int appId) {
        List<AppPO> pos = appMapper.queryById(appId);
        return AppDTO.convert(pos);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int insert(MultipartFile file, byte updateType, int[] softChannel, String context, String extra,
                      HttpServletRequest req) {
        AppPO appPO = new AppPO();
        // 根据url对应的apk获取版本名称、版本号、文件大小
        appPO.setUrl(FileUtil.uploadFile(file, req));
        appPO.setVersionname(file.getOriginalFilename());
        appPO.setVersioncode(1);
        appPO.setSize((int) file.getSize());

        appPO.setUpdateType(updateType);
        appPO.setContext(context);
        appPO.setExtra(extra);

        appPO.setCreateTime(new Date());
        appPO.setaId(1);
        appPO.setStatus(1);
        appPO.setDr((byte) 1);

        int frist = appMapper.insert(appPO);

        List<AppChPO> appChPOs = new ArrayList<>();
        for (int chanId : softChannel) {
            AppChPO appChPO = new AppChPO();
            appChPO.setAppId(appPO.getAppId());
            appChPO.setSoftChannelId(chanId);
            appChPO.setStatus((byte) 1);
            appChPOs.add(appChPO);
        }

        int secend = appChMapper.batchInsert(appChPOs);

        return frist + secend;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int update(int appId, MultipartFile file, byte updateType, int[] softChannel, String context, String extra, HttpServletRequest req) {
        AppPO appPO = appMapper.selectByPrimaryKey(appId);
        if (file != null && !"".equals(file)) {
            // 根据url对应的apk获取版本名称、版本号、文件大小
            appPO.setUrl(FileUtil.uploadFile(file, req));
            appPO.setVersionname(file.getOriginalFilename());
            appPO.setVersioncode(2);
            appPO.setSize((int) file.getSize());

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


        return frist + secend + third;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int updateStatus(int appId, int status) {
        AppPO appPO = appMapper.selectByPrimaryKey(appId);
        appPO.setPublishTime(status == 2 ? new Date() : null);
        appPO.setStatus(status);
        int frist = appMapper.updateByPrimaryKey(appPO);

        int secend = appChMapper.updateStatus(appId, status);

        return frist + secend;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(int appId) {
        int frist = appMapper.deleteByPrimaryKey(appId);
        int secend = appChMapper.deleteByAppId(appId);
        return frist + secend;
    }
}
