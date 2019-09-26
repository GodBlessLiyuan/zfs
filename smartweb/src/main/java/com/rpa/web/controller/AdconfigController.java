package com.rpa.web.controller;

import com.rpa.web.dto.AdconfigDTO;
import com.rpa.web.pojo.AdconfigPO;
import com.rpa.web.service.AdconfigService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
     * 新增广告
     * @param
     * @return
     */
    @PostMapping("insert")
    public int insert(AdconfigDTO adconfigDTO){
        return this.adconfigService.insert(adconfigDTO);
    }

    /**
     * 修改广告
     * @param adconfigDTO
     * @return
     */
    @PostMapping("update")
    public int update(AdconfigDTO adconfigDTO){
        return this.adconfigService.update(adconfigDTO);
    }

    /**
     * 删除广告
     * @param adId
     * @return
     */
    @PostMapping("delete")
    public int delete(int adId) {
        return this.adconfigService.delete(adId);
    }
}
