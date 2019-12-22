package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.rpa.common.utils.LogUtil;
import com.rpa.common.utils.RedisKeyUtil;
import com.rpa.web.common.PageHelper;
import com.rpa.web.vo.FunctionVideoVO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.common.mapper.FunctionvideoMapper;
import com.rpa.common.pojo.FunctionvideoPO;
import com.rpa.web.service.FunctionVideoService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.FileUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.utils.OperatorUtil;
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
 * @date: Created in 16:19 2019/10/9
 * @version: 1.0.0
 * @description:
 */
@Service
public class FunctionVideoServiceImpl implements FunctionVideoService {
    private final static Logger logger = LoggerFactory.getLogger(FunctionVideoServiceImpl.class);

    @Resource
    private FunctionvideoMapper functionVideoMapper;

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
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
    public DTPageInfo<FunctionVideoVO> query(int draw, int start, int length, String funName) {

        // 分页
        Page<FunctionVideoVO> page = PageHelper.startPage(start, length);

        // 创建map对象，封装查询条件，作为动态sql语句的参数
        Map<String, Object> map = new HashMap<>(1);
        map.put("funName", funName);

        // 按照条件查询数据
        List<FunctionvideoPO> pos = functionVideoMapper.query(map);

        // 将查询到的 po 数据转换为 vo
        List<FunctionVideoVO> vos = new ArrayList<>();
        for(FunctionvideoPO po: pos) {
            FunctionVideoVO vo = new FunctionVideoVO();
            vo.setFunctionId(po.getFunctionId());
            vo.setFunName(po.getFunName());
            if (null == po.getUrl()) {
                vo.setUrl(po.getUrl());
            } else {
                vo.setUrl(publicPath + po.getUrl());
            }
            vo.setExtra(po.getExtra());
            vo.setOperator(queryUsernameByAid(po.getaId()));

            vos.add(vo);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), vos);
    }


    /**
     * 查询
     * @param functionId
     * @return
     */
    @Override
    public ResultVO queryById(Integer functionId) {

        FunctionvideoPO po = this.functionVideoMapper.selectByPrimaryKey(functionId);
        if (null == po) {
            return new ResultVO(1002);
        }

        FunctionVideoVO vo = new FunctionVideoVO();
        vo.setFunName(po.getFunName());
        vo.setExtra(po.getExtra());

        return new ResultVO<>(1000, vo);
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

        FunctionvideoPO po = new FunctionvideoPO();

        // 对funname进行唯一性校验，如果有重名，不给插入
        int funname_count = this.functionVideoMapper.queryFunname(funName);
        if (funname_count == 0) {
            po.setFunName(funName);
        }else {
            return new ResultVO(1003);
        }

        if (null == url) {
            po.setUrl(null);
        } else {
            po.setUrl(FileUtil.uploadFile(url, videoDir, "functionvideo"));
        }
        po.setExtra(extra);
        po.setUpdateTime(new Date());
        po.setCreateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        int result = this.functionVideoMapper.insert(po);
        if (result == 0) {
            LogUtil.log(logger, "insert", "插入失败", po);
        }
        return new ResultVO(1000);
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

        // 从数据库中查询出要修改的数据
        FunctionvideoPO po = this.functionVideoMapper.selectByPrimaryKey(functionId);

        if (null == po) {
            return new ResultVO(1002);
        }

        po.setFunName(funName);
        if (null != url) {
            po.setUrl(FileUtil.uploadFile(url, videoDir, "functionvideo"));
        }
        po.setExtra(extra);
        po.setUpdateTime(new Date());
        po.setaId(OperatorUtil.getOperatorId(httpSession));

        int result = this.functionVideoMapper.updateByPrimaryKey(po);
        if (result == 0) {
            LogUtil.log(logger, "update", "更新失败", po);
        }

        //删除Redis
        String funname = this.queryFunnameById(functionId);
        deleteRedis(funname);

        return new ResultVO(1000);
    }

    /**
     * 删除
     * @param functionId
     * @return
     */
    @Override
    public ResultVO delete(Integer functionId) {

        int result = this.functionVideoMapper.deleteByPrimaryKey(functionId);
        if (result == 0) {
            LogUtil.log(logger, "delete", "删除失败", functionId);
        }

        //删除Redis
        String funname = this.queryFunnameById(functionId);
        deleteRedis(funname);

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
