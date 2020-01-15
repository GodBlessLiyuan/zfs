package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.BlackAppMapper;
import com.rpa.common.pojo.BlackAppPO;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.common.PageHelper;
import com.rpa.web.service.IBlackAppService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.BlankAppVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BlackAppServiceImpl implements IBlackAppService {
    private final static Logger logger = LoggerFactory.getLogger(BlackAppServiceImpl.class);

    @Resource
    private BlackAppMapper blackAppMapper;

    @Override
    public DTPageInfo<BlankAppVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<BlackAppPO> page = PageHelper.startPage(pageNum, pageSize);
        List<BlackAppPO> pos = blackAppMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), BlankAppVO.convert(pos));
    }

    @Override
    public ResultVO insert(String packageName, String appName, String extra, int aId) {
        BlackAppPO po = new BlackAppPO();

        po.setPackageName(packageName);
        po.setAppName(appName);
        po.setExtra(extra);
        po.setCreateTime(new Date());
        po.setaId(aId);

        int result = blackAppMapper.insert(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
        }

        return new ResultVO(1000);
    }

    @Override
    public ResultVO delete(long blankId) {
        blackAppMapper.deleteByPrimaryKey(blankId);
        return new ResultVO(1000);
    }
}
