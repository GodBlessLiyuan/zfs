package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.AppMapper;
import com.rpa.common.mapper.FeedbackMapper;
import com.rpa.common.mapper.UserMapper;
import com.rpa.common.pojo.AppPO;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.web.utils.DateUtil;
import com.rpa.web.vo.FeedbackVO;
import com.rpa.common.pojo.FeedbackPO;
import com.rpa.web.service.FeedbackService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: dangyi
 * @date: Created in 9:16 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AppMapper appMapper;

    @Value("${file.publicPath}")
    private String publicPath;

    @Resource
    private StringRedisTemplate template;


    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param startTime
     * @param endTime
     * @param userId
     * @param contact
     * @return
     */
    @Override
    public DTPageInfo<FeedbackVO> query(int draw, int start, int length, String startTime, String endTime, String userId, String contact) {

        // 分页
        Page<FeedbackVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(4);
        map.put("startTime", startTime);
        map.put("endTime", DateUtil.str2str1(endTime));
        map.put("userId", userId);
        map.put("contact", contact);

        // 按照条件查询数据
        List<FeedbackPO> pos = this.feedbackMapper.query(map);

        // po转换为vo
        List<FeedbackVO> vos = new ArrayList<>();
        for(FeedbackPO po: pos) {
            FeedbackVO vo = new FeedbackVO();
            vo.setFeedbackId(po.getFeedbackId());
            vo.setPhone(queryPhoneByUserId(po.getUserId()));
            vo.setDeviceId(po.getDeviceId());
            vo.setManufacturer(po.getManufacturer());
            vo.setAndroidmodel(po.getAndroidmodel());
            vo.setBuildrelease(po.getBuildrelease());
            vo.setVersionname(queryVersionnameByVersioncode(po.getVersioncode()));
            vo.setCreateTime(po.getCreateTime());
            vo.setContact(po.getContact());
            vo.setContext(po.getContext());
            if (null == po.getUrl1()) {
                vo.setUrl1(po.getUrl1());
            } else {
                vo.setUrl1(publicPath + po.getUrl1());
            }
            if (null == po.getUrl2()) {
                vo.setUrl2(po.getUrl2());
            } else {
                vo.setUrl2(publicPath + po.getUrl2());
            }
            if (null == po.getUrl3()) {
                vo.setUrl3(po.getUrl3());
            } else {
                vo.setUrl3(publicPath + po.getUrl3());
            }

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }


    /**
     * 根据ID，从t_user表中查询出手机号码（账号）
     * @param userId
     * @return
     */
    public String queryPhoneByUserId(Long userId) {
        return this.userMapper.queryPhoneByUserId(userId);
    }

    /**
     * 根据版本序号查询版本号
     * @param versioncode
     * @return
     */
    private String queryVersionnameByVersioncode(int versioncode) {
        //Redis的key
        String key = RedisKeyUtil.genFeedbackRedisKey("versionname");
        //先尝试从Redis中查询
        String versionname;
        versionname = (String) this.template.opsForHash().get(key, String.valueOf(versioncode));
        if (null == versionname) {
            //从数据库中查
            versionname = this.appMapper.queryVersionameByVersioncode(versioncode);
            //从表中查出所有的versioncode和versionname
            List<AppPO> pos = this.appMapper.queryCodesAndNames();
            //封装成map
            Map<String, String> map = new HashMap<>();
            for(AppPO po : pos) {
                map.put(String.valueOf(po.getVersioncode()), po.getVersionname());
            }
            //存入Redis
            this.template.opsForHash().putAll(key, map);
            this.template.expire(key, 1, TimeUnit.HOURS);
        }
        return versionname;
    }
}
