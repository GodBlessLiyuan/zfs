package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.UserGiftsBO;
import com.zfs.common.mapper.ComTypeMapper;
import com.zfs.common.mapper.UserGiftsMapper;
import com.zfs.common.pojo.ComTypePO;
import com.zfs.common.pojo.UserGiftsPO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.service.IUserGiftsSercive;
import com.zfs.web.vo.UserGiftsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/3 16:53
 * @description: 新用户送会员
 * @version: 1.0
 */
@Service
public class UserGiftsServiceImpl implements IUserGiftsSercive {
    private final static Logger logger = LoggerFactory.getLogger(UserGiftsServiceImpl.class);

    @Resource
    private UserGiftsMapper userGiftsMapper;

    @Resource
    private ComTypeMapper comTypeMapper;
    //使用@Resource注解报错
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ResultVO query(Integer pageNum, Integer pageSize, Map<String, Object> reqData) {
        Page<UserGiftsBO> page = PageHelper.startPage(pageNum, pageSize);
        List<UserGiftsBO> pos = userGiftsMapper.query(reqData);

        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), UserGiftsVO.convert(pos)));
    }

    @Override
    public ResultVO insert(int comTypeId, int aId) {
        ComTypePO comTypePO = comTypeMapper.selectByPrimaryKey(comTypeId);

        UserGiftsPO userGiftsPO = new UserGiftsPO();
        userGiftsPO.setComTypeId(comTypeId);
        userGiftsPO.setComTypeName(comTypePO.getName());
        userGiftsPO.setDays(comTypePO.getDays());

        userGiftsPO.setStatus((byte) 1);
        userGiftsPO.setCreateTime(new Date());
        userGiftsPO.setaId(aId);
        userGiftsPO.setDr((byte) 1);

        int result = userGiftsMapper.insert(userGiftsPO);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", userGiftsPO);
            return ResultVO.serverInnerError();
        }
        this.redisdelete();
        return new ResultVO(1000);
    }

    private void redisdelete() {
        String deviceDay = RedisKeyUtil.genRedisKey("device", "days");
        redisTemplate.delete(deviceDay);
    }

    @Override
    public ResultVO updateStatus(int nugId, byte status) {
        if (2 == status) {
            // 开启
            // 不能同时开启多个
            List<UserGiftsPO> pos = userGiftsMapper.queryByStatus((byte) 2);
            if (pos != null && pos.size() > 0) {
                return new ResultVO(4002, "不能同时开启多个！");
            }
        }

        UserGiftsPO po = userGiftsMapper.selectByPrimaryKey(nugId);
        po.setStatus(status);
        int result = userGiftsMapper.updateByPrimaryKey(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "修改失败", po);
            return ResultVO.serverInnerError();
        }
        this.redisdelete();
        return new ResultVO(1000);
    }

    @Override
    public ResultVO delete(int nugId) {
        int result = userGiftsMapper.deleteByPrimaryKey(nugId);
        if (result == 0) {
            LogUtil.log(logger, "delete", "删除失败", nugId);
            return ResultVO.serverInnerError();
        }
        this.redisdelete();
        return new ResultVO(1000);
    }
}
