package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.domain.AdChannelDO;
import com.rpa.web.dto.AdChannelDTO;
import com.rpa.web.dto.AppDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.mapper.AdChannelMapper;
import com.rpa.web.mapper.AppMapper;
import com.rpa.web.mapper.SoftChannelMapper;
import com.rpa.web.pojo.AdChannelPO;
import com.rpa.web.pojo.AppPO;
import com.rpa.web.service.AdChannelService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public DTPageInfo<AdChannelDTO> query(int draw, int start, int length, int adId, String name, int appId) {

        // 分页
        Page<AdChannelDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", name);
        map.put("appId", appId);

        // 按照条件，从t_app和t_soft_channel表中联合查询数据，该数据中不含状态值
        List<AdChannelDO> lists_DO = this.adChannelMapper.query(map);

        // 根据查询到的数据的三个ID，往中间表t_ad_channel中查询状态值
        // 如果中间表无此条数据，就新建，新建时状态值默认为1
        // 然后再将所查询到的所有do数据转换为带着状态值的dto，返回
        List<AdChannelDTO> lists_DTO = new ArrayList<>();
        for (AdChannelDO do1 : lists_DO) {
            AdChannelDTO dto = new AdChannelDTO();

            //往中间表t_ad_channel中查询
            AdChannelDO do2 = this.adChannelMapper.queryByIds(adId, do1.getAppId(), do1.getSoftChannelId());
            if (null == do2) {
                //先往中间表t_ad_channel中插入数据
                AdChannelPO adChannelPO = new AdChannelPO();
                adChannelPO.setAdId(adId);
                adChannelPO.setSoftChannelId(do1.getSoftChannelId());
                adChannelPO.setAppId(do1.getAppId());
                adChannelPO.setCreateTime(new Date());
                adChannelPO.setUpdateTime(new Date());
                adChannelPO.setType((byte)1);
                adChannelPO.setDr((byte)1);
                this.adChannelMapper.insert(adChannelPO);

                //再次查询，将数据转化为dto，返回
                AdChannelDO do3 = new AdChannelDO();
                dto = do2dto(do3);
            } else {
                //将数据转换为dto，返回
                dto = do2dto(do2);
            }

            lists_DTO.add(dto);
        }
        //封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 将do数据转换为dto
     * @param adChannelDO
     * @return
     */
    private AdChannelDTO do2dto(AdChannelDO adChannelDO) {
        AdChannelDTO dto = new AdChannelDTO();
        dto.setAdId(adChannelDO.getAdId());
        dto.setSoftChannelId(adChannelDO.getSoftChannelId());
        dto.setAppId(adChannelDO.getAppId());
        dto.setType(adChannelDO.getType());
        dto.setVersionname(adChannelDO.getVersionname());
        dto.setName(adChannelDO.getName());

        return dto;
    }

    /**
     * 查询版本名称
     * @return
     */
    @Override
    public ResultVO queryVersionname() {

        List<AppPO> appPOS = this.appMapper.queryVersionname();

        if (null == appPOS) {
            return ResultVOUtil.error(ExceptionEnum.QUERY_ERROR);
        }

        // 将 po 转换为 dto 返回给前端
        List<AppDTO> dtos = new ArrayList<>();
        for (AppPO po : appPOS) {
            AppDTO dto = new AppDTO();
            dto.setAppId(po.getAppId());
            dto.setVersionName(po.getVersionname());

            dtos.add(dto);
        }
        return ResultVOUtil.success(dtos);
    }


    /**
     * 修改
     * @param list
     * @return
     */
    @Override
    @Transactional
    public ResultVO update(List<AdChannelDTO> list) {

        // 对传过来的 DTOs 进行迭代，并转换为 PO
        for (AdChannelDTO dto : list) {

            // 先在数据库中查询出要修改的数据
            AdChannelPO po = this.adChannelMapper.queryByIds2(dto.getAdId(), dto.getAppId(), dto.getSoftChannelId());

            if (null == po) {
                return ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
            }

            int type = dto.getType();
            if (type == 1) {
                po.setType((byte) 2);
            } else {
                po.setType((byte) 1);
            }
            po.setUpdateTime(new Date());

            this.adChannelMapper.update(po);
        }

        return ResultVOUtil.success();
    }
}
