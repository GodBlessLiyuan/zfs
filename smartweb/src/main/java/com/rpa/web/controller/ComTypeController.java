package com.rpa.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.pojo.AdminUserPO;
import com.rpa.web.pojo.ComTypePO;
import com.rpa.web.service.IComTypeService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/25 9:49
 * @description: 产品列表
 * @version: 1.0
 */
@RestController
public class ComTypeController {

    @Resource
    private IComTypeService service;

    @RequestMapping("/comtype/insert")
    public void insert(@RequestParam(value = "name") String name, @RequestParam(value = "days") int days,
                       @RequestParam(value = "extra") String extra, HttpSession session) {

        AdminUserPO loginUser = (AdminUserPO)session.getAttribute("loginUser");

        ComTypePO po = new ComTypePO();
        po.setName(name);
        po.setDays(days);
        po.setExtra(extra);
        po.setaId(loginUser.getaId());
        po.setCreateTime(new Date());

        service.insert(po);
    }

    @RequestMapping("/comtype/query")
    public DTPageInfo<ComTypePO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                       @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "username") String username) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("username", username);

        Page<ComTypePO> page = PageHelper.startPage(pageNum, pageSize);
        List<ComTypePO> data = service.query(map);

        return new DTPageInfo<>(draw, page.getTotal(), data);
    }
}
