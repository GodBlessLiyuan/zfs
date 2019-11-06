package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.domain.AdChannelDO;
import com.rpa.web.dto.AdChannelDTO;
import com.rpa.web.dto.AppDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.mapper.AdChannelMapper;
import com.rpa.web.mapper.AppMapper;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Object> map = new HashMap<>(3);
        map.put("adId", adId);
        map.put("name", name);
        map.put("appId", appId);

        // 按照条件查询数据
        List<AdChannelDO> lists_DO = this.adChannelMapper.query(map);

        // 将查询到的 DO 数据转换为 DTO
        List<AdChannelDTO> lists_DTO = new ArrayList<>();
        for (AdChannelDO adChannelDO : lists_DO) {
            AdChannelDTO dto = new AdChannelDTO();
            dto.setAdId(adChannelDO.getAdId());
            dto.setName(adChannelDO.getName());
            dto.setVersionname(adChannelDO.getVersionname());
            dto.setType(adChannelDO.getType());
            dto.setSoftChannelId(adChannelDO.getSoftChannelId());
            dto.setAppId(adChannelDO.getAppId());

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
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
            AdChannelPO po = this.adChannelMapper.queryByIds(dto.getAdId(), dto.getAppId(), dto.getSoftChannelId());

            if (null == po) {
                return ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
            }

            int type = dto.getType();
            if (type == 1) {
                po.setType((byte) 2);
            } else {
                po.setType((byte) 1);
            }

            this.adChannelMapper.update(po);
        }

        return ResultVOUtil.success();
    }
}
