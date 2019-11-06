package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.web.common.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.dto.BannerConfigDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.mapper.AdminUserMapper;
import com.rpa.web.mapper.BannerConfigMapper;
import com.rpa.web.pojo.BannerConfigPO;
import com.rpa.web.service.BannerConfigService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.FileUtil;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author: dangyi
 * @date: Created in 18:52 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@Service
public class BannerConfigServiceImpl implements BannerConfigService {

    @Autowired
    private BannerConfigMapper bannerConfigMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Value("${file.iconDir}")
    private String iconDir;

    @Value("${file.publicPath}")
    private String publicPath;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param name
     * @param status
     * @return
     */
    @Override
    public DTPageInfo<BannerConfigDTO> query(int draw, int start, int length, String name, Byte status) {

        // 分页
        Page<BannerConfigDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", name);
        map.put("status", status);

        // 按照条件查询数据
        List<BannerConfigPO> lists_PO = bannerConfigMapper.query(map);

        // 将查询到的 PO 数据转换为 DTO
        List<BannerConfigDTO> lists_DTO = new ArrayList<>();
        for (BannerConfigPO po : lists_PO) {
            BannerConfigDTO dto = new BannerConfigDTO();
            dto.setBannerId(po.getBannerId());
            dto.setName(po.getName());
            dto.setStartTime(po.getStartTime());
            if (null == po.getPicPath()) {
                dto.setPicPath(po.getPicPath());
            } else {
                dto.setPicPath(publicPath + po.getPicPath());
            }
            dto.setUrl(po.getUrl());
            dto.setStatus(po.getStatus());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 插入
     * @param
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO insert(String name, MultipartFile picPath, String url, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        BannerConfigPO po = new BannerConfigPO();
        po.setName(name);
        po.setPicPath(FileUtil.uploadFile(picPath, iconDir, "banner"));
        po.setUrl(url);
        po.setCreateTime(new Date());
        po.setUpdateTime(new Date());
        po.setStatus((byte)1);
        po.setDr((byte)1);
        po.setaId(aId);//测试的时候，暂且写为1，正常参数应为aId

        int count = this.bannerConfigMapper.insert(po);
        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.INSERT_ERROR);
    }

    /**
     * 修改状态
     * @param dto
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO update(BannerConfigDTO dto, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 先查出要修改的数据
        BannerConfigPO po = this.bannerConfigMapper.selectByPrimaryKey(dto.getBannerId());
        if (null == po) {
            return ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
        }

        if (po.getStatus() == 1) {
            po.setStatus((byte) 2);
            po.setStartTime(new Date());
        } else {
            po.setStatus((byte)1);
        }
        po.setUpdateTime(new Date());
        po.setaId(aId);//测试的时候，暂且写为1，正常参数应为aId

        int count = this.bannerConfigMapper.updateByPrimaryKey(po);
        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
    }

    /**
     * 删除
     * @param bannerId
     * @return
     */
    @Override
    public ResultVO delete(int bannerId) {
        int count = this.bannerConfigMapper.deleteByPrimaryKey(bannerId);
        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.DELETE_ERROR);
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
