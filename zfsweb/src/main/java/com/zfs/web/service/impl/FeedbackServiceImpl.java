package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.bo.FeedbackBO;
import com.zfs.common.mapper.AppMapper;
import com.zfs.common.mapper.FeedbackMapper;
import com.zfs.common.mapper.UserMapper;
import com.zfs.common.pojo.AppPO;
import com.zfs.common.pojo.FeedbackPO;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.common.vo.PageInfoVO;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.common.PageHelper;
import com.zfs.web.service.FeedbackService;
import com.zfs.web.utils.DateUtil;
import com.zfs.web.vo.FeedbackVO;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     *
     * @param start
     * @param length
     * @param startTime
     * @param endTime
     * @param phone
     * @param contact
     * @return
     */
    @Override
    public ResultVO query(Integer start, Integer length, String startTime, String endTime, String phone, String contact) {

        // 分页
        Page<FeedbackVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(4);
        map.put("startTime", startTime);
        map.put("endTime", DateUtil.str2str1(endTime));
        map.put("phone", phone);
        map.put("contact", contact);

        // 按照条件查询数据
        List<FeedbackBO> bos = this.feedbackMapper.query(map);

        // po转换为vo
        List<FeedbackVO> vos = new ArrayList<>();
        for (FeedbackBO bo : bos) {
            FeedbackVO vo = new FeedbackVO();
            vo.setFeedbackId(bo.getFeedbackId());
            vo.setContext(bo.getContext());
            vo.setContact(bo.getContact());
            vo.setCreateTime(bo.getCreateTime());
            vo.setManufacturer(bo.getManufacturer());
            vo.setAndroidmodel(bo.getAndroidmodel());
            vo.setBuildrelease(bo.getBuildrelease());
            vo.setVersionname(queryVersionnameByVersioncode(bo.getVersioncode()));
            vo.setPhone(bo.getPhone());
            vo.setUserId(bo.getUserId());
            vo.setDeviceId(bo.getDeviceId());
            vo.setUserDeviceId(bo.getUserDeviceId());
            vo.setVersioncode(bo.getVersioncode());


            if (StringUtils.isEmpty(bo.getUrl1())) {
                vo.setUrl1(bo.getUrl1());
            } else {
                vo.setUrl1(publicPath + bo.getUrl1());
            }
            if (StringUtils.isEmpty(bo.getUrl2())) {
                vo.setUrl2(bo.getUrl2());
            } else {
                vo.setUrl2(publicPath + bo.getUrl2());
            }
            if (StringUtils.isEmpty(bo.getUrl3())) {
                vo.setUrl3(bo.getUrl3());
            } else {
                vo.setUrl3(publicPath + bo.getUrl3());
            }

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new ResultVO(1000, new PageInfoVO<>(page.getTotal(), vos));
    }


    /**
     * 根据ID，从t_user表中查询出手机号码（账号）
     *
     * @param userId
     * @return
     */
    public String queryPhoneByUserId(Long userId) {
        return this.userMapper.queryPhoneByUserId(userId);
    }

    /**
     * 根据版本序号查询版本号
     *
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
            for (AppPO po : pos) {
                map.put(String.valueOf(po.getVersioncode()), po.getVersionname());
            }
            //存入Redis
            this.template.opsForHash().putAll(key, map);
            this.template.expire(key, 1, TimeUnit.HOURS);
        }
        return versionname;
    }
}
