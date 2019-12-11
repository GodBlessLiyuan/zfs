package com.rpa.web.controller;

import com.rpa.common.constant.Constant;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.service.IComTypeService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.vo.ComTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/25 9:49
 * @description: 产品列表
 * @version: 1.0
 */
@RequestMapping("comtype")
@RestController
public class ComTypeController {

    @Autowired
    private IComTypeService service;

    @RequestMapping("query")
    public DTPageInfo<ComTypeVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                       @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "username") String username) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("username", username);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("queryAll")
    public List<ComTypeVO> queryAll() {
        return service.queryAll();
    }

    @RequestMapping("insert")
    public ResultVO insert(@RequestParam(value = "name") String name,
                           @RequestParam(value = "days") int days,
                           @RequestParam(value = "extra") String extra, HttpServletRequest req) {
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }

        return service.insert(name, days, extra, admin.getaId());
    }
}
