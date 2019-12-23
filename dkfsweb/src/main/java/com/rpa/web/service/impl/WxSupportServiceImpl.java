package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.WxsupportMapper;
import com.rpa.common.pojo.WxsupportPO;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.web.vo.WxSupportVO;
import com.rpa.web.service.IWxSupportService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 11:11
 * @description: 微信白名单
 * @version: 1.0
 */
@Service
public class WxSupportServiceImpl implements IWxSupportService {
    private final static Logger logger = LoggerFactory.getLogger(WxSupportServiceImpl.class);

    @Resource
    private WxsupportMapper wxSupportMapper;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public DTPageInfo<WxSupportVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<WxsupportPO> page = PageHelper.startPage(pageNum, pageSize);
        List<WxsupportPO> pos = wxSupportMapper.query(reqData);
        return new DTPageInfo<>(draw, page.getTotal(), WxSupportVO.convert(pos));
    }

    @Override
    public ResultVO insert(String packageName, String extra, int aId) {
        WxsupportPO po = new WxsupportPO();

        po.setPackageName(packageName);
        po.setExtra(extra);
        po.setCreateTime(new Date());

        po.setaId(aId);

        int result = wxSupportMapper.insert(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "删除失败", po);
        }

        //删除Redis
        deleteRedis();

        return new ResultVO(1000);
    }

    @Override
    public int delete(int wId) {

        //删除Redis
        deleteRedis();

        return wxSupportMapper.deleteByPrimaryKey(wId);
    }


    /**
     * @author：dangyi 删除Redis
     */
    private void deleteRedis() {
        Set<String> redisKeys = template.keys(RedisKeyUtil.genSupportRedisKey("*"));
        if (!CollectionUtils.isEmpty(redisKeys)) {
            template.delete(redisKeys);
        }
    }
}
