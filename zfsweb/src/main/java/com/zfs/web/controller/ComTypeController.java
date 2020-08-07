package com.zfs.web.controller;

import com.zfs.common.constant.Constant;
import com.zfs.common.vo.ResultVO;
import com.zfs.web.dto.AdminUserDTO;
import com.zfs.web.service.IComTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    public ResultVO query(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                          @RequestParam(value = "username", required = false) String username) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("username", username);

        return service.query(pageNum, pageSize, reqData);
    }

    @RequestMapping("queryAll")
    public ResultVO queryAll() {
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
