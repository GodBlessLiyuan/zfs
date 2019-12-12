package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.mapper.ShareActivityMapper;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.web.utils.OperatorUtil;
import com.rpa.web.vo.ShareActivityVO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.common.pojo.ShareActivityPO;
import com.rpa.web.service.ShareActivityService;
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
 * @date: Created in 10:19 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@Service
public class ShareActivityServiceImpl implements ShareActivityService {

    @Autowired
    private ShareActivityMapper shareActivityMapper;

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
     * @param type
     * @return
     */
    @Override
    public DTPageInfo<ShareActivityVO> query(int draw, int start, int length, Byte type) {

        // 分页
        Page<ShareActivityVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("type", type);

        // 按照条件查询数据
        List<ShareActivityPO> pos = this.shareActivityMapper.query(map);

        // 将查询到的 po 数据转换为 vo
        List<ShareActivityVO> vos = new ArrayList<>();
        for(ShareActivityPO po: pos) {
            ShareActivityVO vo = new ShareActivityVO();
            vo.setMaterialId(po.getMaterialId());
            vo.setType(po.getType());
            if (po.getType() == 1) {
                vo.setContent(po.getContent());
            } else {
                vo.setContent(publicPath + po.getContent());
            }
            vo.setExtra(po.getExtra());
            vo.setOperator(queryUsernameByAid(po.getaId()));

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }


    /**
     * 插入
     * @param type
     * @param contentText
     * @param contentImage
     * @param extra
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO insert(Byte type, String contentText, MultipartFile contentImage, String extra, HttpSession httpSession) {

        // 创建一个 po 对象
        ShareActivityPO po = new ShareActivityPO();
        po.setType(type);
        if (type == 1) {
            po.setContent(contentText);
        } else {
            po.setContent(FileUtil.uploadFile(contentImage, iconDir, "share"));
        }
        po.setExtra(extra);
        po.setCreateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        this.shareActivityMapper.insert(po);

        //删除Redis
        deleteRedis();

        return new ResultVO(1000);
    }


    /**
     * 修改
     * @param type
     * @param contentText
     * @param contentImage
     * @param extra
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO update(Integer materialId, Byte type, String contentText, MultipartFile contentImage, String extra, HttpSession httpSession) {

        // 根据material_id字段，从表t_share_activity中查出要修改的数据
        ShareActivityPO po = this.shareActivityMapper.selectByPrimaryKey(materialId);
        po.setType(type);
        if (type == 1) {
            po.setContent(contentText);
        } else {
            po.setContent(FileUtil.uploadFile(contentImage, iconDir, "share"));
        }
        po.setExtra(extra);
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        this.shareActivityMapper.updateByPrimaryKey(po);

        //删除Redis
        deleteRedis();

        return new ResultVO(1000);
    }


    /**
     * 删除
     * @param materialId
     * @return
     */
    @Override
    public ResultVO delete(int materialId) {

        this.shareActivityMapper.deleteByPrimaryKey(materialId);

        //删除Redis
        deleteRedis();

        return new ResultVO(1000);
    }


    /**
     * 查询：根据ID查询数据
     * @param materialId
     * @return
     */
    @Override
    public ResultVO queryById(Integer materialId) {

        ShareActivityPO po = this.shareActivityMapper.selectByPrimaryKey(materialId);
        if (null == po) {
            return new ResultVO(1002);
        }

        // 将查询到的 po 转换为 dto
        ShareActivityVO dto = new ShareActivityVO();
        dto.setType(po.getType());
        if (po.getType() == 1) {
            dto.setContent(po.getContent());
        } else {
            dto.setContent(publicPath + po.getContent());
        }
        dto.setExtra(po.getExtra());

        return new ResultVO<>(1000, dto);
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
        //Redis中的key
        String key = RedisKeyUtil.genShareRedisKey();
        if (template.hasKey(key)) {
            template.delete(key);
        }
    }
}
