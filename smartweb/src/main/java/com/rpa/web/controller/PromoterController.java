package com.rpa.web.controller;

import com.rpa.web.dto.PromoterDTO;
import com.rpa.web.service.PromoterService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: dangyi
 * @date: Created in 14:15 2019/9/27
 * @version: 1.0.0
 * @description: 会员卡推广--推广负责人
 */

@RestController
@RequestMapping("promoter")
public class PromoterController {

    @Autowired
    private PromoterService promoterService;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param proName
     * @param phone
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<PromoterDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "proName", required = false) String proName,
                                         @RequestParam(value = "phone", required = false) String phone
    ) {
        // 调用业务层，返回页面结果
        DTPageInfo<PromoterDTO> dTPageInfo = promoterService.query(draw, pageNum, pageSize, proName, phone);
        return dTPageInfo;
    }

    /**
     * 插入
     * @param promoterDTO
     * @return
     */
    @PostMapping("insert")
    public int insert(PromoterDTO promoterDTO) {
        return this.promoterService.insert(promoterDTO);
    }

    /**
     * 修改
     * @param promoterDTO
     * @return
     */
    @PostMapping("update")
    public int update(PromoterDTO promoterDTO){
        return this.promoterService.update(promoterDTO);
    }
}
