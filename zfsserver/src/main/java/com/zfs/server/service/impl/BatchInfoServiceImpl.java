package com.zfs.server.service.impl;

import com.zfs.common.constant.UserVipConstant;
import com.zfs.common.mapper.BatchInfoMapper;
import com.zfs.common.mapper.UserVipMapper;
import com.zfs.common.pojo.BatchInfoPO;
import com.zfs.common.pojo.UserVipPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.constant.BatchInfoConstant;
import com.zfs.server.dto.BatchInfoDTO;
import com.zfs.server.service.IBatchInfoService;
import com.zfs.server.utils.RedisMapUtil;
import com.zfs.server.utils.UserVipUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author: xiahui
 * @date: Created in 2019/10/29 9:28
 * @description: 卡密表
 * @version: 1.0
 */
@EnableTransactionManagement
@Service
public class BatchInfoServiceImpl implements IBatchInfoService {
    private final static Logger logger = LoggerFactory.getLogger(BatchInfoServiceImpl.class);

    @Resource
    private BatchInfoMapper batchInfoMapper;
    @Resource
    private UserVipMapper userVipMapper;
    @Resource
    private RedisMapUtil redisMapUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO activate(BatchInfoDTO dto) {
        BatchInfoPO po = batchInfoMapper.queryByKey(dto.getKey());
        if (null == po) {
            return new ResultVO(1016);
        }
        //冻结，3
        if (BatchInfoConstant.FROZEN == po.getStatus()) {
            return new ResultVO(1017);
        } else if (BatchInfoConstant.ACTIVATED == po.getStatus()) {
            return new ResultVO(1020);
        }
        //失效 4,6,7，
        else if (BatchInfoConstant.EXPIRED == po.getStatus() || BatchInfoConstant.OVER_EXPIRED == po.getStatus() ||
                BatchInfoConstant.OVER_FROZEN == po.getStatus()) {
            return new ResultVO(1021);
        }
        //结束的提示语：状态码：1021:卡密已过期，无法使用
        else if (BatchInfoConstant.END == po.getStatus()) {
            return new ResultVO(1021);
        }
        po.setUserId(dto.getUd());
        po.setStatus((byte) 2);
        po.setUpdateTime(new Date());
        int result1 = batchInfoMapper.updateByPrimaryKey(po);
        if (result1 == 0) {
            LogUtil.log(logger, "activate", "更新失败", po);
        }

        // 更新用户会员数据
        UserVipPO userVipPO = userVipMapper.queryByUserId(dto.getUd());
        UserVipPO newUserVipPO = UserVipUtil.buildUserVipVO(userVipPO, dto.getUd(), po.getDays(), false);
        int result2;
        if (userVipPO == null) {
            result2 = userVipMapper.insert(newUserVipPO);
        } else {
            result2 = userVipMapper.updateByPrimaryKey(newUserVipPO);
        }
        if (result2 == 0) {
            LogUtil.log(logger, "activate", "更新用户会员数据失败", newUserVipPO);
        }
        //删除缓存
        String key = RedisKeyUtil.genRedisKey(UserVipConstant.UserID, dto.getUd());
        redisMapUtil.hdel(key);
        return new ResultVO(1000);
    }


}
