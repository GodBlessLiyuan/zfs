package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.OtherAppDTO;
import com.rpa.web.mapper.OtherAppMapper;
import com.rpa.web.pojo.OtherAppPO;
import com.rpa.web.service.IOtherAppService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 20:02
 * @description: 其他产品
 * @version: 1.0
 */
@Service
public class OtherAppServiceImpl implements IOtherAppService {

    @Resource
    private OtherAppMapper otherAppMapper;

    @Override
    public DTPageInfo<OtherAppDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<OtherAppPO> page = PageHelper.startPage(pageNum, pageSize);
        List<OtherAppPO> pos = otherAppMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), OtherAppDTO.convert(pos));
    }

    @Override
    public int insert(String oName, String extra, String iconUrl, byte downloadType, String appUrl) {
        OtherAppPO po = new OtherAppPO();

        po.setaId(1);
        po.setoName(oName);
        po.setExtra(extra);
        po.setIconUrl(iconUrl);
        po.setDownloadType(downloadType);
        po.setAppUrl(appUrl);
        po.setCreateTime(new Date());

        return otherAppMapper.insert(po);
    }

    @Override
    public int delete(int oId) {
        return otherAppMapper.deleteByPrimaryKey(oId);
    }
}