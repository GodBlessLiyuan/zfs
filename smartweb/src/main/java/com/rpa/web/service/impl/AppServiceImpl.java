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

    @Transactional(rollbackFor = {})
    @Override
    public int insert(String url, byte updateType, int[] softChannel, String context, String extra) {
        AppPO appPO = new AppPO();
        // 根据url对应的apk获取版本名称、版本号、文件大小
        appPO.setVersionname("xxxxxxx");
        appPO.setVersioncode(1);
        appPO.setSize(120000);

        appPO.setUrl(url);
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
}
