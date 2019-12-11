package com.rpa.web.controller;

import com.rpa.common.dto.AdconfigDTO;
import com.rpa.web.service.AdconfigService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.rpa.web.common.Constant.SHOW_INTERVAL;


/**
 * @author: dangyi
 * @date: Created in 17:08 2019/9/25
 * @version: 1.0.0
 * @description: 开屏广告
 */

@RestController
@RequestMapping("adconfig")
public class AdconfigController {

    @Autowired
    private AdconfigService adconfigService;

    /**
     * 查询
     * @param start
     * @param length
     * @param name
     * @param adNumber
     * @param status
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<AdconfigDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                         @RequestParam(value = "start", defaultValue = "1") int start,
                                         @RequestParam(value = "length", defaultValue = "10") int length,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "adNumber", required = false) String adNumber,
                                         @RequestParam(value = "status", required = false) Byte status
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<AdconfigDTO> dTPageInfo = adconfigService.query(draw, start, length, name, adNumber, status);
        return dTPageInfo;
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @GetMapping("queryById")
    public ResultVO queryById (@RequestParam(value = "adId") int id) {
        return this.adconfigService.queryById(id);
    }

    /**
     * 查询：展示广告策略
     * @return
     */
    @GetMapping("/query/strategy")
    public ResultVO queryStrategy( ) {
        return this.adconfigService.queryStrategy(SHOW_INTERVAL);
    }

    /**
     * 插入
     *
     * @param
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(AdconfigDTO adconfigDTO, HttpSession httpSession) {
        return this.adconfigService.insert(adconfigDTO, httpSession);
    }

    /**
     * 修改
     * @param adconfigDTO
     * @return
     */
    @PostMapping("update")
    public ResultVO update(AdconfigDTO adconfigDTO, HttpSession httpSession) {
        return this.adconfigService.update(adconfigDTO, httpSession);
    }

    @PostMapping("/update/status")
    public ResultVO updateStatus(@RequestParam(value = "adId")Integer adId,
                                 @RequestParam(value = "status")Byte status,
                                 HttpSession httpSession) {
        return this.adconfigService.updateStatus(adId, status, httpSession);
    }

    @PostMapping("/update/strategy")
    public ResultVO updateStrategy(@RequestParam(value = "show_interval")String show_interval) {
        return this.adconfigService.updateStrategy(show_interval);
    }


    /**
     * 删除
     * @param adId
     * @return
     */
    @PostMapping("delete")
    public ResultVO delete(int adId) {
        return this.adconfigService.delete(adId);
    }
}
