package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.ShareActivityDTO;
import com.rpa.web.mapper.ShareActivityMapper;
import com.rpa.web.pojo.ShareActivityPO;
import com.rpa.web.service.ShareActivityService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 10:19 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@Service
public class ShareActivityServiceImpl implements ShareActivityService {

    @Autowired
    private ShareActivityMapper shareActivityMapper;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
    @Override
    public DTPageInfo<ShareActivityDTO> query(int draw, int pageNum, int pageSize, int type) {

        // 分页
        Page<ShareActivityDTO> page = PageHelper.startPage(pageNum, pageSize);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("type", type);

        // 按照条件查询数据
        List<ShareActivityPO> lists_PO = this.shareActivityMapper.query(map);

        // 将查询到的 ShareActivityPO 数据转换为 ShareActivityDTO
        List<ShareActivityDTO> lists_DTO = new ArrayList<>();
        for(ShareActivityPO po: lists_PO) {
            ShareActivityDTO dto = new ShareActivityDTO();
            dto.setMaterialId(po.getMaterialId());
            dto.setType(po.getType());
            dto.setContent(po.getContent());
            dto.setExtra(po.getExtra());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 插入
     * @param shareActivityDTO
     * @param httpSession
     * @return
     * @TODO 缺少管理员a_id这个字段，值需从session中取
     */
    @Override
    public int insert(ShareActivityDTO shareActivityDTO, HttpSession httpSession) {

        // 把 shareActivityDTO 转换为 shareActivityPO
        ShareActivityPO shareActivityPO = new ShareActivityPO();
        shareActivityPO.setType(shareActivityDTO.getType());
        shareActivityPO.setContent(shareActivityDTO.getContent());
        shareActivityPO.setExtra(shareActivityDTO.getExtra());
        shareActivityPO.setCreateTime(new Date());
        shareActivityPO.setUpdateTime(new Date());

        int count = this.shareActivityMapper.insert(shareActivityPO);
        return count;
    }

    /**
     * 修改
     * @param shareActivityDTO
     * @param httpSession
     * @return
     * @TODO 缺乏管理人a_id字段，需从session中获取
     */
    @Override
    public int update(ShareActivityDTO shareActivityDTO, HttpSession httpSession) {

        // 根据material_id字段，从表t_share_activity中查出要修改的数据
        ShareActivityPO shareActivityPO = this.shareActivityMapper.selectByPrimaryKey(shareActivityDTO.getMaterialId());
        shareActivityPO.setType(shareActivityDTO.getType());
        shareActivityPO.setContent(shareActivityDTO.getContent());
        shareActivityPO.setExtra(shareActivityDTO.getExtra());
        shareActivityPO.setUpdateTime(new Date());

        int count = this.shareActivityMapper.updateByPrimaryKey(shareActivityPO);
        return count;
    }

    /**
     * 删除
     * @param materialId
     * @return
     */
    @Override
    public int delete(int materialId) {
        return this.shareActivityMapper.deleteByPrimaryKey(materialId);
    }

    /**
     * 根据aId，从t_admin_user表中查询username
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.shareActivityMapper.queryUsernameByAid(aId);
    }
}
