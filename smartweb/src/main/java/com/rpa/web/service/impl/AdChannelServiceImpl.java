package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.pojo.AdChannelPO;
import com.rpa.web.common.PageHelper;
import com.rpa.common.bo.AdChannelBO;
import com.rpa.web.dto.AdChannelDTO;
import com.rpa.web.dto.AppDTO;
import com.rpa.common.mapper.AdChannelMapper;
import com.rpa.common.mapper.AppMapper;
import com.rpa.common.mapper.SoftChannelMapper;
import com.rpa.common.pojo.AppPO;
import com.rpa.web.service.AdChannelService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.vo.AdChannelVO;
import com.rpa.web.vo.AppVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


/**
 * @author: dangyi
 * @date: Created in 18:44 2019/10/11
 * @version: 1.0.0
 * @description:
 */
@Service
@EnableTransactionManagement
public class AdChannelServiceImpl implements AdChannelService {

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
     * @param draw
     * @param start
     * @param length
     * @param adId
     * @param name
     * @param appId
     * @return
     */
    @Override
    public DTPageInfo<AdChannelVO> query(int draw, int start, int length, int adId, String name, int appId) {

        // 分页
        Page<AdChannelVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", name);
        map.put("appId", appId);

        // 按照条件，从t_app和t_soft_channel表中联合查询数据，该数据中不含状态值
        List<AdChannelBO> bos = this.adChannelMapper.query(map);

        // 根据查询到的数据的三个ID，往中间表t_ad_channel中查询状态值
        // 如果中间表无此条数据，就新建，新建时状态值默认为1
        // 然后再将所查询到的所有bo数据转换为带着状态值的vo，返回
        List<com.rpa.web.vo.AdChannelVO> vos = new ArrayList<>();
        for (AdChannelBO bo1 : bos) {

            AdChannelVO vo;

            //往中间表t_ad_channel中查询
            AdChannelBO bo2 = this.adChannelMapper.queryByIds(adId, bo1.getAppId(), bo1.getSoftChannelId());
            if (null == bo2) {
                //先往中间表t_ad_channel中插入数据
                AdChannelPO po = new AdChannelPO();
                po.setAdId(adId);
                po.setSoftChannelId(bo1.getSoftChannelId());
                po.setAppId(bo1.getAppId());
                po.setCreateTime(new Date());
                po.setUpdateTime(new Date());
                po.setType((byte)1);
                po.setDr((byte)1);
                this.adChannelMapper.insert(po);

                //再次查询，将数据转化为vo，返回
                AdChannelBO bo3 = new AdChannelBO();
                vo = bo2vo(bo3);
            } else {
                //将数据转换为vo，返回
                vo = bo2vo(bo2);
            }

            vos.add(vo);
        }
        //封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }

    /**
     * 将do数据转换为dto
     * @param bo
     * @return
     */
    private AdChannelVO bo2vo(AdChannelBO bo) {
        AdChannelVO vo = new AdChannelVO();
        vo.setAdId(bo.getAdId());
        vo.setSoftChannelId(bo.getSoftChannelId());
        vo.setAppId(bo.getAppId());
        vo.setType(bo.getType());
        vo.setVersionname(bo.getVersionname());
        vo.setName(bo.getName());

        return vo;
    }

    /**
     * 查询版本名称
     * @return
     */
    @Override
    public ResultVO queryVersionname() {

        List<AppPO> pos = this.appMapper.queryVersionname();

        if (null == pos) {
            return new ResultVO<>(1000, new ArrayList<>());
        }

        // 将 po 转换为 vo 返回给前端
        List<AppVO> vos = new ArrayList<>();
        for (AppPO po : pos) {
            AppVO vo = new AppVO();
            vo.setAppId(po.getAppId());
            vo.setVersionName(po.getVersionname());

            vos.add(vo);
        }
        return new ResultVO<>(1000, vos);
    }


    /**
     * 修改：开/关广告渠道
     * @param dtos
     * @return
     */
    @Override
    @Transactional
    public ResultVO update(List<AdChannelDTO> dtos) {

        // 对传过来的 dtos 进行迭代，并转换为 po
        for (AdChannelDTO dto : dtos) {

            // 先在数据库中查询出要修改的数据
            AdChannelPO po = this.adChannelMapper.queryByIds2(dto.getAdId(), dto.getAppId(), dto.getSoftChannelId());

            if (null == po) {
                return new ResultVO(1002);
            }

            int type = dto.getType();
            if (type == 1) {
                po.setType((byte) 2);
            } else {
                po.setType((byte) 1);
            }
            po.setUpdateTime(new Date());

            this.adChannelMapper.update(po);

            //删除Redis
            this.deleteRedis(dto.getSoftChannelId());
        }

        return new ResultVO(1000);
    }


    /**
     * 删除Redis，根据soft_channel_id
     */
    private void deleteRedis(Integer softChannelId) {
        String name = this.softChannelMapper.queryNameById(softChannelId);
        List<Integer> versioncodes = this.appMapper.queryVersioncodes();
        for (Integer versioncode : versioncodes) {
            String key = "smarthelper" + "adconfig" + name + versioncode;
            if (template.hasKey(key)) {
                this.template.delete(key);
            }
        }
    }
}
