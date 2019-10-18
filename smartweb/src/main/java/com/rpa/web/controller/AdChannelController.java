package com.rpa.web.controller;

import com.rpa.web.dto.AdChannelDTO;
import com.rpa.web.service.AdChannelService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: dangyi
 * @date: Created in 18:33 2019/10/11
 * @version: 1.0.0
 * @description:
 */
@RestController
@RequestMapping("adchannel")
public class AdChannelController {

    @Autowired
    private AdChannelService adChannelService;

    /**
     * 设置开放渠道——查询
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<AdChannelDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                          @RequestParam(value = "adId", required = false) int adId,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "appId", required = false) int appId
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<AdChannelDTO> dTPageInfo = adChannelService.query(draw, pageNum, pageSize, adId, name, appId);
        return dTPageInfo;
    }


    /**
     * 查询：查询t_app表中所有的版本名称
     */
    @GetMapping("queryVersionname")
    public ResultVO queryVersionname() {
        return this.adChannelService.queryVersionname();
    }

    /**
     * 设置开发渠道——修改
     * 即是否开启广告
     * @param list
     * @return
     */
    @PostMapping("update")
    public ResultVO update(@RequestBody List<AdChannelDTO> list) {
        return this.adChannelService.update(list);
    }
}
