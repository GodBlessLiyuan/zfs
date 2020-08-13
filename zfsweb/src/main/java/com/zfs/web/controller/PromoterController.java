package com.zfs.web.controller;

import com.zfs.web.dto.PromoterDTO;
import com.zfs.web.service.PromoterService;
import com.zfs.common.vo.ResultVO;
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
     * @param proName
     * @param phone
     * @return
     */
    @PostMapping("query")
    public ResultVO query(
          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
          @RequestParam(value = "proName", required = false) String proName,
          @RequestParam(value = "phone", required = false) String phone
    ) {
        // 调用业务层，返回页面结果
        return promoterService.query(pageNum, pageSize, proName, phone);
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
