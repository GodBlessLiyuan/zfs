package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.mapper.BlackAppMapper;
import com.zfs.common.pojo.BlackAppPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.service.IBlackAppService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.web.vo.BlankAppVO;
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
