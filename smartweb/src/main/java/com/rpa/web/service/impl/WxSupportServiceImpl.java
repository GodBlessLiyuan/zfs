package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.WxSupportDTO;
import com.rpa.web.mapper.WxSupportMapper;
import com.rpa.web.pojo.WxSupportPO;
import com.rpa.web.service.IWxSupportService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 11:11
 * @description: 微信白名单
 * @version: 1.0
 */
@Service
public class WxSupportServiceImpl implements IWxSupportService {

    @Resource
    private WxSupportMapper wxSupportMapper;

    @Override
    public DTPageInfo<WxSupportDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<WxSupportPO> page = PageHelper.startPage(pageNum, pageSize);
        List<WxSupportPO> pos = wxSupportMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), WxSupportDTO.convert(pos));
    }

    @Override
    public ResultVO insert(String packageName, String extra, int aId) {
        WxSupportPO po = new WxSupportPO();

        po.setPackageName(packageName);
        po.setExtra(extra);
        po.setCreateTime(new Date());

        po.setaId(aId);

        wxSupportMapper.insert(po);

        return ResultVOUtil.success();
    }

    @Override
    public int delete(int wId) {
        return wxSupportMapper.deleteByPrimaryKey(wId);
    }
}
