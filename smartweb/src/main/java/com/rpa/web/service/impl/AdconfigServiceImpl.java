package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.*;
import com.rpa.common.pojo.AdconfigPO;
import com.rpa.common.pojo.KeyValuePO;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.common.constant.Constant;
import com.rpa.web.dto.AdconfigDTO;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.dto.KeyValueDTO;
import com.rpa.web.service.AdconfigService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.utils.OperatorUtil;
import com.rpa.web.vo.AdconfigVO;
import com.rpa.web.vo.KeyValueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import static com.rpa.common.constant.Constant.SHOW_INTERVAL;

/**
 * @author: dangyi
 * @date: Created in 18:56 2019/9/25
 * @version: 1.0.0
 * @description:
 */

@Service
public class AdconfigServiceImpl implements AdconfigService {

    @Autowired
    private AdconfigMapper adconfigMapper;

    @Autowired
    private KeyValueMapper keyValueMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdChannelMapper adChannelMapper;

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private SoftChannelMapper softChannelMapper;

    @Autowired
    private StringRedisTemplate template;

    /**
     * 查询
     *
     * @param draw
     * @param start
     * @param length
     * @param name
     * @param adNumber
     * @param status
     * @return
     */
    @Override
    public DTPageInfo<AdconfigVO> query(int draw, int start, int length, String name, String adNumber, Byte status) {

        // 分页
        Page<AdconfigVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", name);
        map.put("adNumber", adNumber);
        map.put("status", status);

        // 按照条件查询数据
        List<AdconfigPO> pos = this.adconfigMapper.query(map);

        // 将查询到的 po 数据转换为 vo
        List<AdconfigVO> vos = new ArrayList<>();
        for (AdconfigPO po : pos) {
            AdconfigVO vo = po2vo(po);
            String username = queryUsernameByAid(po.getaId());
            vo.setOperator(username);

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }

    /**
     * 查询广告策略
     * @param showInterval
     * @return
     */
    @Override
    public ResultVO queryStrategy(int showInterval) {

        KeyValuePO po = this.keyValueMapper.selectByPrimaryKey(showInterval);
        if (null == po) {
            return new ResultVO(1002);
        }
        KeyValueVO vo = new KeyValueVO();
        vo.setKeyName(po.getKeyName());
        vo.setValue(po.getValue());

        return new ResultVO<>(1000, vo);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public ResultVO queryById(int id) {

        AdconfigPO po = this.adconfigMapper.selectByPrimaryKey(id);

        if (null == po) {
            return new ResultVO(1002);
        } else {
            return new ResultVO<>(1000, po2vo(po));
        }
    }


    /**
     * 插入
     *
     * @param dto
     */
    @Override
    public ResultVO insert(AdconfigDTO dto, HttpSession httpSession) {

        // dto 转换为 po
        AdconfigPO po = new AdconfigPO();
        po.setAdNumber(dto.getAdNumber());
        po.setaId(OperatorUtil.getOperatorId(httpSession));
        po.setName(dto.getName());
        po.setContacts(dto.getContacts());
        po.setPhone(dto.getPhone());
        po.setPriority(dto.getPriority());
        po.setTotal(dto.getTotal());
        po.setUpdateTime(new Date());
        po.setCreateTime(new Date());
        po.setStatus((byte) 1);
        po.setDr((byte) 1);

        this.adconfigMapper.insert(po);
        return new ResultVO(1000);
    }

    /**
     * 修改广告配置信息
     *
     * @param dto
     * @param httpSession
     */
    @Override
    public ResultVO update(AdconfigDTO dto, HttpSession httpSession) {

        // 根据 ad_id，从数据库获取要修改的数据对象
        AdconfigPO po = this.adconfigMapper.selectByPrimaryKey(dto.getAdId());

        // 把 dto 转换为 po
        po.setAdNumber(dto.getAdNumber());
        po.setName(dto.getName());
        po.setContacts(dto.getContacts());
        po.setPhone(dto.getPhone());
        po.setPriority(dto.getPriority());
        po.setTotal(dto.getTotal());
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        this.adconfigMapper.updateByPrimaryKey(po);

        //删除Redis
        this.deleteRedis(dto.getAdId());

        return new ResultVO(1000);
    }

    /**
     * 修改状态
     *
     * @param adId
     * @param status
     * @param httpSession
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO updateStatus(Integer adId, Byte status, HttpSession httpSession) {

        // 根据主键ad_id，从数据库查出要修改的数据
        AdconfigPO po = this.adconfigMapper.selectByPrimaryKey(adId);

        if (null == po) {
            return new ResultVO(1002);
        }

        po.setStatus(status);
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        //修改中间表t_ad_channel表中当前ad_id数据的状态值
        this.adChannelMapper.update2(adId, status);

        //删除Redis
        this.deleteRedis(adId);

        this.adconfigMapper.updateByPrimaryKey(po);

        return new ResultVO(1000);
    }

    /**
     * 修改广告展现间隔
     *
     * @param show_interval
     * @return
     */
    @Override
    public ResultVO updateStrategy(String show_interval) {

        KeyValuePO po = new KeyValuePO();
        po.setKeyName(SHOW_INTERVAL);
        po.setValue(show_interval);

        // 先查询下t_key_value表中是否有SHOW_INTERVAL数据
        KeyValuePO keyValuePO = this.keyValueMapper.selectByPrimaryKey(SHOW_INTERVAL);
        if (null == keyValuePO) {
            this.keyValueMapper.insert(po);
        } else {
            this.keyValueMapper.updateByPrimaryKey(po);
        }

        //删除Redis
        this.deleteRedis();

        return new ResultVO(1000);
    }



    /**
     * 删除广告
     *
     * @param adId
     * @return
     */
    @Override
    public ResultVO delete(int adId) {

        this.adconfigMapper.deleteByPrimaryKey(adId);

        //删除Redis
        this.deleteRedis(adId);

        return new ResultVO(1000);
    }


    /**
     * AdconfigPO 转换为 AdconfigVO
     * @param po
     * @return
     */
    private AdconfigVO po2vo(AdconfigPO po) {
        AdconfigVO vo = new AdconfigVO();
        vo.setAdId(po.getAdId());
        vo.setAdNumber(po.getAdNumber());
        vo.setContacts(po.getContacts());
        vo.setCreateTime(po.getCreateTime());
        vo.setName(po.getName());
        vo.setPhone(po.getPhone());
        vo.setPriority(po.getPriority());
        vo.setStatus(po.getStatus());
        vo.setTotal(po.getTotal());
        vo.setUpdateTime(po.getUpdateTime());
        vo.setDr(po.getDr());
        return vo;
    }


    /**
     * 根据aId，从t_admin_user表中查询username
     *
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.adminUserMapper.queryUsernameByAid(aId);
    }


    /**
     * 删除Redis，根据广告ID
     */
    private void deleteRedis(Integer adId) {
        List<Integer> softChannelIds = this.adChannelMapper.querySoftChannelIdsByAdId(adId);
        List<Integer> versioncodes = this.appMapper.queryVersioncodes();
        for (Integer softChannelId : softChannelIds) {
            String name = this.softChannelMapper.queryNameById(softChannelId);
            for (Integer versioncode : versioncodes) {
                String key = RedisKeyUtil.genAdconfigRedisKey() + name + versioncode;
                if (template.hasKey(key)) {
                    template.delete(key);
                }
            }
        }
    }

    /**
     * 删除Redis，所有的
     */
    private void deleteRedis(){
        List<String> names = this.softChannelMapper.queryNames();
        List<Integer> versioncodes = this.appMapper.queryVersioncodes();
        for (String name : names) {
            for (Integer versioncode : versioncodes) {
                String key = RedisKeyUtil.genAdconfigRedisKey() + name + versioncode;
                if (template.hasKey(key)) {
                    template.delete(key);
                }
            }
        }
    }
}
