package com.rpa.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.pojo.VipCommodityPO;
import com.rpa.web.service.IVipCommodityService;
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
 * @date: Created in 2019/9/26 11:49
 * @description: 商品列表
 * @version: 1.0
 */
@RestController
public class VipCommodityController {

    @Resource
    private IVipCommodityService service;

    @RequestMapping("/vipcommodity/query")
    public DTPageInfo<VipCommodityPO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                            @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                            @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                            @RequestParam(value = "username") String username,
                                            @RequestParam(value = "comname") String comname,
                                            @RequestParam(value = "channelname") String channelname) {
        Map<String, Object> map = new HashMap<>(3);
        // 操作员
        map.put("username", username);
        // 商品类型
        map.put("comname", comname);
        // 渠道名
        map.put("channelname", channelname);

        Page<VipCommodityPO > page = PageHelper.startPage(pageNum, pageSize);
        List<VipCommodityPO > data = service.query(map);

        return new DTPageInfo<>(draw, page.getTotal(), data);
    }
}
