package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.common.Constant;
import com.rpa.web.common.PageHelper;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.dto.FunctionVideoDTO;
import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.web.mapper.FunctionVideoMapper;
import com.rpa.web.pojo.FunctionVideoPO;
import com.rpa.web.service.FunctionVideoService;
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
 * @date: Created in 16:19 2019/10/9
 * @version: 1.0.0
 * @description:
 */
@Service
public class FunctionVideoServiceImpl implements FunctionVideoService {

    @Autowired
    private FunctionVideoMapper functionVideoMapper;

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private StringRedisTemplate template;

    @Value("${file.videoDir}")
    private String videoDir;

    @Value("${file.publicPath}")
    private String publicPath;


    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param funName
     * @return
     */
    @Override
    public DTPageInfo<FunctionVideoDTO> query(int draw, int start, int length, String funName) {

        // 分页
        Page<FunctionVideoDTO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("funName", funName);

        // 按照条件查询数据
        List<FunctionVideoPO> lists_PO = functionVideoMapper.query(map);

        // 将查询到的 FunctionVideoPO 数据转换为 FunctionVideoDTO
        List<FunctionVideoDTO> lists_DTO = new ArrayList<>();
        for(FunctionVideoPO po: lists_PO) {
            FunctionVideoDTO dto = new FunctionVideoDTO();
            dto.setFunctionId(po.getFunctionId());
            dto.setFunName(po.getFunName());
            if (null == po.getUrl()) {
                dto.setUrl(po.getUrl());
            } else {
                dto.setUrl(publicPath + po.getUrl());
            }
            dto.setExtra(po.getExtra());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }


    /**
     * 查询
     * @param functionId
     * @return
     */
    @Override
    public ResultVO queryById(Integer functionId) {

        FunctionVideoPO po = this.functionVideoMapper.selectByPrimaryKey(functionId);
        if (null == po) {
            return ResultVOUtil.error(ExceptionEnum.QUERY_ERROR);
        }

        FunctionVideoDTO dto = new FunctionVideoDTO();
        dto.setFunName(po.getFunName());
        dto.setExtra(po.getExtra());

        return ResultVOUtil.success(dto);
    }


    /**
     * 插入
     * @param httpSession
     * @param funName
     * @param url
     * @param extra
     * @return
     */
    @Override
    public ResultVO insert(HttpSession httpSession, String funName, MultipartFile url, String extra) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        FunctionVideoPO po = new FunctionVideoPO();

        // 对funname进行唯一性校验，如果有重名，不给插入
        int funname_count = this.functionVideoMapper.queryFunname(funName);
        if (funname_count == 0) {
            po.setFunName(funName);
        }else {
            return ResultVOUtil.error(ExceptionEnum.INSERT_ERROR);
        }

        if (null == url) {
            po.setUrl(null);
        } else {
            po.setUrl(FileUtil.uploadFile(url, videoDir, "functionvideo"));
        }
        po.setExtra(extra);
        po.setUpdateTime(new Date());
        po.setCreateTime(new Date());
        po.setaId(aId);

        int count = this.functionVideoMapper.insert(po);
        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.INSERT_ERROR);
    }


    /**
     * 修改
     * @param httpSession
     * @param functionId
     * @param funName
     * @param url
     * @param extra
     * @return
     */
    @Override
    public ResultVO update(HttpSession httpSession, Integer functionId, String funName, MultipartFile url, String extra) {

        // 从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        AdminUserDTO adminUserDTO = (AdminUserDTO) httpSession.getAttribute(Constant.ADMIN_USER);
        int aId = adminUserDTO.getaId();

        // 从数据库中查询出要修改的数据
        FunctionVideoPO po = this.functionVideoMapper.selectByPrimaryKey(functionId);

        if (null == po) {
            return ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
        }

        po.setFunName(funName);
        if (null != url) {
            po.setUrl(FileUtil.uploadFile(url, videoDir, "functionvideo"));
        }
        po.setExtra(extra);
        po.setUpdateTime(new Date());
        po.setaId(aId);

        int count = this.functionVideoMapper.updateByPrimaryKey(po);

        //删除Redis
        String funname = this.queryFunnameById(functionId);
        deleteRedis(funname);

        return count == 1 ? ResultVOUtil.success() : ResultVOUtil.error(ExceptionEnum.UPDATE_ERROR);
    }

    /**
     * 删除
     * @param functionId
     * @return
     */
    @Override
    public ResultVO delete(Integer functionId) {
        int count = this.functionVideoMapper.deleteByPrimaryKey(functionId);

        //删除Redis
        String funname = this.queryFunnameById(functionId);
        deleteRedis(funname);

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

    /**
     * 根据functionId，从t_functionvideo表中查询funname
     */
    private String queryFunnameById(Integer functionId) {
        return this.functionVideoMapper.queryFunnameById(functionId);

    }


    /**
     * 删除Redis
     */
    private void deleteRedis(String funname) {
        //Redis中的key
        String key = RedisKeyUtil.genFunctionvideoRedisKey() + funname;
        if (template.hasKey(key)) {
            template.delete(key);
        }
    }
}
