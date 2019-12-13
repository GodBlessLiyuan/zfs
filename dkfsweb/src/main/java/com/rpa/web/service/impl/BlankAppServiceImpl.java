package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.BlankAppMapper;
import com.rpa.common.pojo.BlankAppPO;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.common.PageHelper;
import com.rpa.web.service.IBlankAppService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.BlankAppVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/12/12 15:59
 * @description: 应用黑名单
 * @version: 1.0
 */
@Service
public class BlankAppServiceImpl implements IBlankAppService {

    @Resource
    private BlankAppMapper blankAppMapper;

    @Override
    public DTPageInfo<BlankAppVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<BlankAppPO> page = PageHelper.startPage(pageNum, pageSize);
        List<BlankAppPO> pos = blankAppMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), BlankAppVO.convert(pos));
    }

    @Override
    public ResultVO insert(String packageName, String appName, String extra, int aId) {
        BlankAppPO po = new BlankAppPO();

        po.setPackageName(packageName);
        po.setAppName(appName);
        po.setExtra(extra);
        po.setCreateTime(new Date());
        po.setaId(aId);

        blankAppMapper.insert(po);

        return new ResultVO(1000);
    }

    @Override
    public ResultVO delete(long blankId) {
        blankAppMapper.deleteByPrimaryKey(blankId);
        return new ResultVO(1000);
    }
}
