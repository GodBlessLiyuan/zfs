package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.PageHelper;
import com.rpa.web.common.Constant;
import com.rpa.web.dto.AdconfigDTO;
import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.dto.KeyValueDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.mapper.AdconfigMapper;
import com.rpa.web.mapper.AdminUserMapper;
import com.rpa.web.mapper.KeyValueMapper;
import com.rpa.web.pojo.AdconfigPO;
import com.rpa.web.pojo.KeyValuePO;
import com.rpa.web.service.AdconfigService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

import static com.rpa.web.common.Constant.SHOW_INTERVAL;
import static com.rpa.web.enumeration.ExceptionEnum.QUERY_ERROR;

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
    /**
     * 查询
     *
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param name
     * @param adNumber
     * @param status
     * @return
     */
    @Override
    public DTPageInfo<AdconfigDTO> query(int draw, int pageNum, int pageSize, String name, String adNumber, Byte status) {

        // 分页
        Page<AdconfigDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", name);
        map.put("adNumber", adNumber);
        map.put("status", status);

        // 按照条件查询数据
        List<AdconfigPO> lists_PO = adconfigMapper.query(map);

        // 将查询到的 AdconfigPO 数据转换为 AdconfigDTO
        List<AdconfigDTO> lists_DTO = new ArrayList<>();
        for (AdconfigPO po : lists_PO) {
            AdconfigDTO dto = new AdconfigDTO();
            dto.setAdId(po.getAdId());
            dto.setAdNumber(po.getAdNumber());
            dto.setName(po.getName());
            dto.setCreateTime(po.getCreateTime());
            dto.setContacts(po.getContacts());
            dto.setPhone(po.getPhone());
            dto.setPriority(po.getPriority());
            dto.setTotal(po.getTotal());
            dto.setStatus(po.getStatus());
            String username = queryUsernameByAid(po.getaId());
            dto.setOperator(username);

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
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
            return ResultVOUtil.success(null);
        }
        KeyValueDTO dto = new KeyValueDTO();
        dto.setKeyName(po.getKeyName());
        dto.setValue(po.getValue());

        return ResultVOUtil.success(dto);
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
            return ResultVOUtil.error(QUERY_ERROR);
        }
        return ResultVOUtil.success(po);
    }


    /**
     * 插入
     *
     * @param dto
     */
    @Override
    public ResultVO insert(AdconfigDTO dto, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 把adconfigDTO 转换为 adconfigPO
        AdconfigPO po = new AdconfigPO();
        po.setAdNumber(dto.getAdNumber());
        po.setaId(aId);//测试的时候，暂且写为1，正常参数应为aId
        po.setName(dto.getName());
        po.setContacts(dto.getContacts());
        po.setPhone(dto.getPhone());
        po.setPriority(dto.getPriority());
        po.setTotal(dto.getTotal());
        po.setUpdateTime(new Date());
        po.setCreateTime(new Date());
        po.setStatus((byte) 1);
        po.setDr((byte) 1);

        int count = this.adconfigMapper.insert(po);
        if (count == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ExceptionEnum.INSERT_ERROR);
    }

    /**
     * 修改
     *
     * @param adconfigDTO
     * @param httpSession
     * @TODO 还需要修改操作人，即管理员a_id字段，需从session中获取
     */
    @Override
    public ResultVO update(AdconfigDTO adconfigDTO, HttpSession httpSession) {

        // 根据 ad_id，从数据库获取要修改的数据对象
        AdconfigPO adconfigPO = this.adconfigMapper.selectByPrimaryKey(adconfigDTO.getAdId());

        // 把 adconfigDTO 转换为 adconfigPO
        adconfigPO.setAdNumber(adconfigDTO.getAdNumber());
        adconfigPO.setName(adconfigDTO.getName());
        adconfigPO.setContacts(adconfigDTO.getContacts());
        adconfigPO.setPhone(adconfigDTO.getPhone());
        adconfigPO.setPriority(adconfigDTO.getPriority());
        adconfigPO.setTotal(adconfigDTO.getTotal());
        adconfigPO.setUpdateTime(new Date());

        int count = adconfigMapper.updateByPrimaryKey(adconfigPO);

        if (count == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
    }

    /**
     * 修改状态
     *
     * @param adId
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO updateStatus(Integer adId, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 根据主键ad_id，从数据库查出要修改的数据
        AdconfigPO po = this.adconfigMapper.selectByPrimaryKey(adId);

        if (null == po) {
            return ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
        }

        if (po.getStatus() == 1) {
            po.setStatus((byte) 2);
        } else {
            po.setStatus((byte)1);
        }

        po.setUpdateTime(new Date());
        po.setaId(aId);//测试的时候，暂且写为1，正常参数应为aId

        int count = this.adconfigMapper.updateByPrimaryKey(po);
        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
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
        if (keyValuePO == null) {
            int count = this.keyValueMapper.insert(po);
            return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.INSERT_ERROR);
        }
        int count = this.keyValueMapper.updateByPrimaryKey(po);
        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
    }



    /**
     * 删除广告
     *
     * @param adId
     * @return
     */
    @Override
    public ResultVO delete(int adId) {
        int count = adconfigMapper.deleteByPrimaryKey(adId);
        if (count == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ExceptionEnum.DELETE_ERROR);
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
}
