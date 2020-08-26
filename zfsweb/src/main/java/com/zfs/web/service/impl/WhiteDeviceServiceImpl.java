package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.WhiteDeviceBO;
import com.zfs.common.mapper.UserDeviceMapper;
import com.zfs.common.mapper.UserMapper;
import com.zfs.common.mapper.WhiteDeviceMapper;
import com.zfs.common.pojo.UserDevicePO;
import com.zfs.common.pojo.UserPO;
import com.zfs.common.pojo.WhiteDevicePO;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.vo.WhiteDeviceVO;
import com.zfs.web.service.IWhiteDeviceService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
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
 * @date: Created in 2019/9/30 14:23
 * @description: 测试白名单
 * @version: 1.0
 */
@Service
public class WhiteDeviceServiceImpl implements IWhiteDeviceService {
    private final static Logger logger = LoggerFactory.getLogger(WhiteDeviceServiceImpl.class);

    @Resource
    private WhiteDeviceMapper whiteDeviceMapper;
    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private UserDeviceMapper userDeviceMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResultVO query( int pageNum, int pageSize, Map<String, Object> reqData) {
        Page<WhiteDeviceBO> page = PageHelper.offsetPage(pageNum, pageSize);
        List<WhiteDeviceBO> pos = whiteDeviceMapper.query(reqData);
        return new ResultVO(1000,new PageInfoVO<>(page.getTotal(), WhiteDeviceVO.convert(pos)));
    }

    @Override
    public ResultVO insert(String phone, String extra, int aId) {
        UserPO userPO = userMapper.queryByPhone(phone);
        if(userPO==null){
            return ResultVO.phoneNoExist();
        }
        List<UserDevicePO> userDevicePOS=userDeviceMapper.queryPOByUser(userPO.getUserId());
        if(userDevicePOS==null||userDevicePOS.size()==0){
            return new ResultVO(1101);// 未找到对应的deviceId
        }
        //用户存在多个设备
       for(UserDevicePO userDevicePO:userDevicePOS){
           //其实只有一条记录
           List<WhiteDevicePO> whiteDevicePOS = whiteDeviceMapper.queryByDeviceId(userDevicePO.getDeviceId());
           if (whiteDevicePOS == null || whiteDevicePOS.size() == 0) {
               // 不存在在白名单里
               WhiteDevicePO po = new WhiteDevicePO();
               po.setDeviceId(userDevicePO.getDeviceId());
               po.setExtra(extra);
               po.setCreateTime(new Date());
               po.setaId(aId);
               int result = whiteDeviceMapper.insert(po);
               if (result == 0) {
                   LogUtil.log(logger, "insert", "插入失败", po);
               }
           }
       }
        this.deleteRedis();
        return new ResultVO(1000);
    }

    @Override
    public int deleteByDeviceId(int deviceId) {
        int first = whiteDeviceMapper.deleteByDeviceId(deviceId);
        if (first == 0) {
            LogUtil.log(logger, "insert", "删除失败", deviceId);
        }
        this.deleteRedis();
        return first;
    }


    /**
     * 删除对应的Redis
     */
    private void deleteRedis() {
        Set<String> redisKeys = template.keys(RedisKeyUtil.genWhiteDeviceRedisKey("*"));
        if (!CollectionUtils.isEmpty(redisKeys)) {
            template.delete(redisKeys);
        }
    }
}
