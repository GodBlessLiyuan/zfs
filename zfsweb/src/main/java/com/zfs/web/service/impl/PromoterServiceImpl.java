package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.mapper.PromoterMapper;
import com.zfs.common.utils.LogUtil;
import com.zfs.web.common.PageHelper;
import com.zfs.web.dto.PromoterDTO;
import com.zfs.common.mapper.AdminUserMapper;
import com.zfs.common.pojo.PromoterPO;
import com.zfs.web.service.PromoterService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.utils.OperatorUtil;
import com.zfs.web.vo.PromoterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 14:22 2019/9/27
 * @version: 1.0.0
 * @description: TODO
 */
@Service
public class PromoterServiceImpl implements PromoterService {
    private final static Logger logger = LoggerFactory.getLogger(PromoterServiceImpl.class);

    @Resource
    private PromoterMapper promoterMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param proName
     * @param phone
     * @return
     */
    @Override
    public DTPageInfo<PromoterVO> query(int draw, int start, int length, String proName, String phone) {

        // 分页
        Page<PromoterVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("proName", proName);
        map.put("phone", phone);

        // 按照条件查询数据
        List<PromoterPO> pos = promoterMapper.query(map);

        // 将查询到的 po 数据转换为 vo
        List<PromoterVO> vos = new ArrayList<>();
        for(PromoterPO po: pos) {
            PromoterVO vo = new PromoterVO();
            vo.setProId(po.getProId());
            vo.setProName(po.getProName());
            vo.setPhone(po.getPhone());
            vo.setExtra(po.getExtra());
            vo.setaId(po.getaId());
            vo.setCreateTime(po.getCreateTime());
            vo.setUpdateTime(po.getUpdateTime());
            vo.setOperator(queryUsernameByAid(po.getaId()));

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }

    /**
     * 插入
     * @param dto
     * @return
     */
    @Override
    public ResultVO insert(PromoterDTO dto, HttpSession httpSession) {

        // 把 dto 转换为 po
        PromoterPO po = new PromoterPO();
        po.setProName(dto.getProName());
        po.setPhone(dto.getPhone());
        po.setExtra(dto.getExtra());
        po.setaId(dto.getaId());
        po.setCreateTime(new Date());
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        int result = this.promoterMapper.insert(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
        }
        return new ResultVO(1000);
    }

    /**
     * 修改
     * @param dto
     * @return
     */
    @Override
    public ResultVO update(PromoterDTO dto, HttpSession httpSession) {

        // 从数据库中查询出要修改的数据
        PromoterPO po = this.promoterMapper.selectByPrimaryKey(dto.getProId());

        if (null == po) {
            return new ResultVO(1002);
        }

        // 把 dto 转换为 po
        po.setProName(dto.getProName());
        po.setPhone(dto.getPhone());
        po.setExtra(dto.getExtra());
        po.setaId(dto.getaId());
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        int result = promoterMapper.updateByPrimaryKey(po);
        if (result == 0) {
            LogUtil.log(logger, "update", "更新失败", po);
        }
        return new ResultVO(1000);

    }

    /**
     * 根据aId，从t_admin_user表中查询username
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.adminUserMapper.queryUsernameByAid(aId);
    }
}
