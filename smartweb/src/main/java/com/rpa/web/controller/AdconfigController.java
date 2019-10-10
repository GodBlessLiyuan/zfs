package com.rpa.web.controller;

import com.rpa.web.dto.AdconfigDTO;
import com.rpa.web.dto.KeyValueDTO;
import com.rpa.web.service.AdconfigService;
import com.rpa.web.utils.DTPageInfo;
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
     * @param pageNum
     * @param pageSize
     * @param name
     * @param adNumber
     * @param status
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<AdconfigDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "adNumber", required = false) String adNumber,
                                         @RequestParam(value = "status", required = false) Byte status
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<AdconfigDTO> dTPageInfo = adconfigService.query(draw, pageNum, pageSize, name, adNumber, status);
        return dTPageInfo;
    }

    /**
     * 查询
     * @return
     */
    @GetMapping("/query/strategy")
    public KeyValueDTO queryStrategy( ) {
        return this.adconfigService.queryStrategy(SHOW_INTERVAL);
    }

    /**
     * 插入
     *
     * @param
     * @return
     */
    @PostMapping("insert")
    public int insert(AdconfigDTO adconfigDTO, HttpSession httpSession) {
        return this.adconfigService.insert(adconfigDTO, httpSession);
    }

    /**
     * 修改
     * @param adconfigDTO
     * @return
     * @TODO 还需设置开放渠道
     */
    @PostMapping("update")
    public int update(AdconfigDTO adconfigDTO, HttpSession httpSession) {
        return this.adconfigService.update(adconfigDTO, httpSession);
    }

    @PostMapping("/update/status")
    public int updateStatus(AdconfigDTO adconfigDTO, HttpSession httpSession) {
        return this.adconfigService.updateStatus(adconfigDTO, httpSession);
    }

    @PostMapping("/update/strategy")
    public int updateStrategy(String show_interval) {
        return this.adconfigService.updateStrategy(show_interval);
    }

   /**
    @PostMapping("/update/channel")
    public int update(AdconfigDTO adconfigDTO, HttpSession httpSession) {
        return this.adconfigService.update(adconfigDTO, httpSession);
    }

    /**
     * 删除
     * @param adId
     * @return
     */
    @PostMapping("delete")
    public int delete(int adId) {
        return this.adconfigService.delete(adId);
    }
}
