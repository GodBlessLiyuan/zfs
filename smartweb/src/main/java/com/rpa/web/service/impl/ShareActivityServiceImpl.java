package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.common.constant.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.dto.ShareActivityDTO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.web.mapper.ShareActivityMapper;
import com.rpa.web.pojo.ShareActivityPO;
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
    public DTPageInfo<ShareActivityDTO> query(int draw, int start, int length, Byte type) {

        // 分页
        Page<ShareActivityDTO> page = PageHelper.startPage(start, length);

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
            if (po.getType() == 1) {
                dto.setContent(po.getContent());
            } else {
                dto.setContent(publicPath + po.getContent());
            }
            dto.setExtra(po.getExtra());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
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
        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

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
        po.setaId(aId);

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

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

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
        po.setaId(aId);

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
        ShareActivityDTO dto = new ShareActivityDTO();
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
