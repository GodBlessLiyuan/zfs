package com.rpa.web.controller;

import com.rpa.web.dto.PromoterDTO;
import com.rpa.web.service.PromoterService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
     * @param start
     * @param length
     * @param proName
     * @param phone
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<PromoterDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                         @RequestParam(value = "start", defaultValue = "1") int start,
                                         @RequestParam(value = "length", defaultValue = "10") int length,
                                         @RequestParam(value = "proName", required = false) String proName,
                                         @RequestParam(value = "phone", required = false) String phone
    ) {
        // 调用业务层，返回页面结果
        DTPageInfo<PromoterDTO> dTPageInfo = promoterService.query(draw, start, length, proName, phone);
        return dTPageInfo;
    }

    /**
     * 插入
     * @param promoterDTO
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(PromoterDTO promoterDTO, HttpSession httpSession) {
        return this.promoterService.insert(promoterDTO, httpSession);
    }

    /**
     * 修改
     * @param promoterDTO
     * @return
     */
    @PostMapping("update")
    public ResultVO update(PromoterDTO promoterDTO, HttpSession httpSession){
        return this.promoterService.update(promoterDTO, httpSession);
    }
}
