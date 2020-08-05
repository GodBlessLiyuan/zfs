package com.zfs.web.service.impl;

import com.github.pagehelper.Page;
import com.zfs.common.mapper.BannerconfigMapper;
import com.zfs.common.utils.LogUtil;
import com.zfs.common.utils.RedisKeyUtil;
import com.zfs.web.common.PageHelper;
import com.zfs.common.mapper.AdminUserMapper;
import com.zfs.common.pojo.BannerconfigPO;
import com.zfs.web.service.BannerConfigService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.web.utils.FileUtil;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.utils.OperatorUtil;
import com.zfs.web.vo.BannerConfigVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
    private final static Logger logger = LoggerFactory.getLogger(BannerConfigServiceImpl.class);

    @Resource
    private BannerconfigMapper bannerconfigMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
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
    public DTPageInfo<BannerConfigVO> query(int draw, int start, int length, String name, Byte status) {

        // 分页
        Page<BannerConfigVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(2);
        map.put("name", name);
        map.put("status", status);

        // 按照条件查询数据
        List<BannerconfigPO> pos = bannerconfigMapper.query(map);

        // 将查询到的 po 数据转换为 vo
        List<BannerConfigVO> vos = new ArrayList<>();
        for (BannerconfigPO po : pos) {
            BannerConfigVO vo = new BannerConfigVO();
            vo.setBannerId(po.getBannerId());
            vo.setName(po.getName());
            vo.setStartTime(po.getStartTime());
            if (null == po.getPicPath()) {
                vo.setPicPath(po.getPicPath());
            } else {
                vo.setPicPath(publicPath + po.getPicPath());
            }
            vo.setUrl(po.getUrl());
            vo.setStatus(po.getStatus());
            vo.setOperator(queryUsernameByAid(po.getaId()));

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }

    /**
     * 插入
     * @param
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO insert(String name, MultipartFile picPath, String url, HttpSession httpSession) {

        BannerconfigPO po = new BannerconfigPO();
        po.setName(name);
        po.setPicPath(FileUtil.uploadFile(picPath, iconDir, "banner"));
        po.setUrl(url);
        po.setCreateTime(new Date());
        po.setUpdateTime(new Date());
        po.setStatus((byte)1);
        po.setDr((byte)1);
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        int result = this.bannerconfigMapper.insert(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
        }

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

        // 先查出要修改的数据
        BannerconfigPO po = this.bannerconfigMapper.selectByPrimaryKey(bannerId);
        if (null == po) {
            return new ResultVO(1002);
        }

        po.setStatus(status);
        if (status == 2 && po.getStartTime() == null) {
            po.setStartTime(new Date());
        }
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        int result = this.bannerconfigMapper.updateByPrimaryKey(po);
        if (result == 0) {
            LogUtil.log(logger, "update", "修改状态失败", po);
        }

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
        int result = this.bannerconfigMapper.deleteByPrimaryKey(bannerId);
        if (result == 0) {
            LogUtil.log(logger, "delete", "删除失败", bannerId);
        }
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
