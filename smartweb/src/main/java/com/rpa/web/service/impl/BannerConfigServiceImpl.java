package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.common.constant.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.dto.BannerConfigDTO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.web.mapper.BannerConfigMapper;
import com.rpa.web.pojo.BannerConfigPO;
import com.rpa.web.service.BannerConfigService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.FileUtil;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    private StringRedisTemplate template;

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
        po.setaId(aId);

        int count = this.bannerConfigMapper.insert(po);

        //删除Redis
        this.deleteRedis();

        return new ResultVO(1000);
    }


    /**
     * 修改状态
     * @param bannerId
     * @param status
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO update(Integer bannerId, Byte status, HttpSession httpSession) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 先查出要修改的数据
        BannerConfigPO po = this.bannerConfigMapper.selectByPrimaryKey(bannerId);
        if (null == po) {
            return new ResultVO(1002);
        }

        po.setStatus(status);
        if (status == 2 && po.getStartTime() == null) {
            po.setStartTime(new Date());
        }
        po.setUpdateTime(new Date());
        po.setaId(aId);

        int count = this.bannerConfigMapper.updateByPrimaryKey(po);

        //删除Redis
        this.deleteRedis();

        return new ResultVO(1000);
    }

    /**
     * 删除
     * @param bannerId
     * @return
     */
    @Override
    public ResultVO delete(int bannerId) {
        int count = this.bannerConfigMapper.deleteByPrimaryKey(bannerId);
        //删除Redis
        this.deleteRedis();
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

    /**
     * 删除Redis
     */
    private void deleteRedis() {
        String key = RedisKeyUtil.genBannerconfigRedisKey();
        if (template.hasKey(key)) {
            template.delete(key);
        }
    }
}
