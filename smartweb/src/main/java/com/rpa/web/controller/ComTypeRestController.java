package com.rpa.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.pojo.ComTypePO;
import com.rpa.web.service.IComTypeService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/25 9:49
 * @description: 会员中心-产品列表-数据交互
 * @version: 1.0
 */
@RestController
public class ComTypeRestController {

    @Resource
    private IComTypeService service;

    @RequestMapping("/comtype/query")
    public DTPageInfo<ComTypePO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                       @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "operator") String username) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("a_id", username);

        Page<ComTypePO> page = PageHelper.startPage(pageNum, pageSize);
        List<ComTypePO> data = service.query(map);

        return new DTPageInfo<>(draw, page.getTotal(), data);
    }
}
