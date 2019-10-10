package com.rpa.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.dto.FunctionVideoDTO;
import com.rpa.web.mapper.FunctionVideoMapper;
import com.rpa.web.pojo.FunctionVideoPO;
import com.rpa.web.service.FunctionVideoService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param funName
     * @return
     */
    @Override
    public DTPageInfo<FunctionVideoDTO> query(int draw, int pageNum, int pageSize, String funName) {

        // 分页
        Page<FunctionVideoDTO> page = PageHelper.startPage(pageNum, pageSize);

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
            dto.setUrl(po.getUrl());
            dto.setExtra(po.getExtra());
            dto.setOperator(queryUsernameByAid(po.getaId()));

            lists_DTO.add(dto);
        }

        //根据分页查询的结果，封装最终的返回结果
        return new DTPageInfo<>(draw, page.getTotal(), lists_DTO);
    }

    /**
     * 插入
     * @param functionVideoDTO
     * @param httpSession
     * @return
     * @TODO 还需插入操作人，即管理员ID
     */
    @Override
    public int insert(FunctionVideoDTO functionVideoDTO, HttpSession httpSession) {

        // 把functionVideoDTO 转换为 functionVideoPO
        FunctionVideoPO functionVideoPO = new FunctionVideoPO();
        functionVideoPO.setFunName(functionVideoDTO.getFunName());
        functionVideoPO.setUrl(functionVideoDTO.getUrl());
        functionVideoPO.setExtra(functionVideoDTO.getExtra());
        functionVideoPO.setUpdateTime(new Date());
        functionVideoPO.setCreateTime(new Date());

        int count = this.functionVideoMapper.insert(functionVideoPO);
        return count;
    }

    /**
     * 根据aId，从t_admin_user表中查询username
     * @param aId
     * @return
     */
    private String queryUsernameByAid(Integer aId) {
        return this.functionVideoMapper.queryUsernameByAid(aId);
    }
}
