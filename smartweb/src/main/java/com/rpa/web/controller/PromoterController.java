package com.rpa.web.controller;

import com.rpa.web.dto.PromoterDTO;
import com.rpa.web.service.PromoterService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;
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
